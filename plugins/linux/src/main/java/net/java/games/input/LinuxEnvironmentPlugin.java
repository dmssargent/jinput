/**
 * Copyright (C) 2003 Jeremy Booth (jeremy@newdawnsoftware.com)
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 * The name of the author may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 */
package net.java.games.input;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Component.Identifier.Axis;
import net.java.games.input.Component.Identifier.Button;
import net.java.games.input.Controller.Type;
import net.java.games.util.plugins.Plugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/** Environment plugin for linux
 * @author elias
 * @author Jeremy Booth (jeremy@newdawnsoftware.com)
 */
public final class LinuxEnvironmentPlugin extends ControllerEnvironment implements Plugin {
    private final static String LIBNAME = "jinput-linux";
    private final static String POSTFIX64BIT = "64";
    private final static LinuxDeviceThread device_thread = new LinuxDeviceThread();
    private static boolean supported = false;

    static {
        String osName = getPrivilegedProperty("os.name", "").trim();
        if (osName.equals("Linux")) {
            supported = true;
            if ("i386".equals(getPrivilegedProperty("os.arch"))) {
                loadLibrary(LIBNAME);
            } else {
                loadLibrary(LIBNAME + POSTFIX64BIT);
            }
        }
    }

    private final Controller[] controllers;
    private final List<LinuxDevice> devices = new ArrayList<>();

    public LinuxEnvironmentPlugin() {
        if (isSupported()) {
            this.controllers = enumerateControllers();
            logln("Linux plugin claims to have found " + controllers.length + " controllers");
            AccessController.doPrivileged(
                    (PrivilegedAction<Object>) () -> {
                        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
                        return null;
                    });
        } else {
            controllers = new Controller[0];
        }
    }

    /**
     * Static utility method for loading native libraries.
     * It will try to load from either the path given by
     * the net.java.games.input.librarypath property
     * or through System.loadLibrary().
     *
     */
    private static void loadLibrary(final String lib_name) {
        AccessController.doPrivileged(
                (PrivilegedAction<Object>) () -> {
                    String lib_path = System.getProperty("net.java.games.input.librarypath");
                    try {
                        if (lib_path != null)
                            System.load(lib_path + File.separator + System.mapLibraryName(lib_name));
                        else
                            System.loadLibrary(lib_name);
                    } catch (UnsatisfiedLinkError e) {
                        logln("Failed to load library: " + e.getMessage());
                        e.printStackTrace();
                        supported = false;
                    }
                    return null;
                });
    }

    private static String getPrivilegedProperty(final String property) {
        return AccessController.doPrivileged((PrivilegedAction<String>) () -> System.getProperty(property));
    }

    private static String getPrivilegedProperty(final String property, final String default_value) {
        return AccessController.doPrivileged((PrivilegedAction<String>) () -> System.getProperty(property, default_value));
    }

    public static <T> T execute(LinuxDeviceTask<T> task) throws IOException {
        return device_thread.execute(task);
    }

    private static Component[] createComponents(List event_components, LinuxEventDevice device) {
        LinuxEventComponent[][] povs = new LinuxEventComponent[4][2];
        List<LinuxComponent> components = new ArrayList<>();
        for (Object event_component1 : event_components) {
            LinuxEventComponent event_component = (LinuxEventComponent) event_component1;
            Identifier identifier = event_component.getIdentifier();

            if (identifier == Axis.POV) {
                int native_code = event_component.getDescriptor().getCode();
                switch (native_code) {
                    case NativeDefinitions.ABS_HAT0X:
                        povs[0][0] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT0Y:
                        povs[0][1] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT1X:
                        povs[1][0] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT1Y:
                        povs[1][1] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT2X:
                        povs[2][0] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT2Y:
                        povs[2][1] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT3X:
                        povs[3][0] = event_component;
                        break;
                    case NativeDefinitions.ABS_HAT3Y:
                        povs[3][1] = event_component;
                        break;
                    default:
                        logln("Unknown POV instance: " + native_code);
                        break;
                }
            } else if (identifier != null) {
                LinuxComponent component = new LinuxComponent(event_component);
                components.add(component);
                device.registerComponent(event_component.getDescriptor(), component);
            }
        }
        for (LinuxEventComponent[] pov : povs) {
            LinuxEventComponent x = pov[0];
            LinuxEventComponent y = pov[1];
            if (x != null && y != null) {
                LinuxComponent controller_component = new LinuxPOV(x, y);
                components.add(controller_component);
                device.registerComponent(x.getDescriptor(), controller_component);
                device.registerComponent(y.getDescriptor(), controller_component);
            }
        }
        Component[] components_array = new Component[components.size()];
        components.toArray(components_array);
        return components_array;
    }

    private static Mouse createMouseFromDevice(LinuxEventDevice device, Component[] components) throws IOException {
        Mouse mouse = new LinuxMouse(device, components, new Controller[]{}, device.getRumblers());
        if (mouse.getX() != null && mouse.getY() != null && mouse.getPrimaryButton() != null)
            return mouse;
        else
            return null;
    }

    private static Keyboard createKeyboardFromDevice(LinuxEventDevice device, Component[] components) throws IOException {
        return new LinuxKeyboard(device, components, new Controller[]{}, device.getRumblers());
    }

    private static Controller createJoystickFromDevice(LinuxEventDevice device, Component[] components, Type type) throws IOException {
        return new LinuxAbstractController(device, components, new Controller[]{}, device.getRumblers(), type);
    }

    private static Controller createControllerFromDevice(LinuxEventDevice device) throws IOException {
        List event_components = device.getComponents();
        Component[] components = createComponents(event_components, device);
        Type type = device.getType();

        if (type == Type.MOUSE) {
            return createMouseFromDevice(device, components);
        } else if (type == Type.KEYBOARD) {
            return createKeyboardFromDevice(device, components);
        } else if (type == Type.STICK || type == Type.GAMEPAD) {
            return createJoystickFromDevice(device, components, type);
        } else
            return null;
    }

    @Nullable
    @Contract(pure = true)
    private static Button getButtonIdentifier(int index) {
        switch (index) {
            case 0:
                return Button._0;
            case 1:
                return Button._1;
            case 2:
                return Button._2;
            case 3:
                return Button._3;
            case 4:
                return Button._4;
            case 5:
                return Button._5;
            case 6:
                return Button._6;
            case 7:
                return Button._7;
            case 8:
                return Button._8;
            case 9:
                return Button._9;
            case 10:
                return Button._10;
            case 11:
                return Button._11;
            case 12:
                return Button._12;
            case 13:
                return Button._13;
            case 14:
                return Button._14;
            case 15:
                return Button._15;
            case 16:
                return Button._16;
            case 17:
                return Button._17;
            case 18:
                return Button._18;
            case 19:
                return Button._19;
            case 20:
                return Button._20;
            case 21:
                return Button._21;
            case 22:
                return Button._22;
            case 23:
                return Button._23;
            case 24:
                return Button._24;
            case 25:
                return Button._25;
            case 26:
                return Button._26;
            case 27:
                return Button._27;
            case 28:
                return Button._28;
            case 29:
                return Button._29;
            case 30:
                return Button._30;
            case 31:
                return Button._31;
            default:
                return null;
        }
    }

    private static Controller createJoystickFromJoystickDevice(LinuxJoystickDevice device) {
        List<AbstractComponent> components = new ArrayList<>();
        byte[] axisMap = device.getAxisMap();
        char[] buttonMap = device.getButtonMap();
        LinuxJoystickAxis[] hatBits = new LinuxJoystickAxis[6];

        for (int i = 0; i < device.getNumButtons(); i++) {
            Identifier button_id = LinuxNativeTypesMap.getButtonID(buttonMap[i]);
            if (button_id != null) {
                LinuxJoystickButton button = new LinuxJoystickButton(button_id);
                device.registerButton(i, button);
                components.add(button);
            }
        }
        for (int i = 0; i < device.getNumAxes(); i++) {
            Axis axis_id;
            axis_id = (Axis) LinuxNativeTypesMap.getAbsAxisID(axisMap[i]);
            LinuxJoystickAxis axis = new LinuxJoystickAxis(axis_id);

            device.registerAxis(i, axis);

            if (axisMap[i] == NativeDefinitions.ABS_HAT0X) {
                hatBits[0] = axis;
            } else if (axisMap[i] == NativeDefinitions.ABS_HAT0Y) {
                hatBits[1] = axis;
                axis = new LinuxJoystickPOV(Axis.POV, hatBits[0], hatBits[1]);
                device.registerPOV((LinuxJoystickPOV) axis);
                components.add(axis);
            } else if (axisMap[i] == NativeDefinitions.ABS_HAT1X) {
                hatBits[2] = axis;
            } else if (axisMap[i] == NativeDefinitions.ABS_HAT1Y) {
                hatBits[3] = axis;
                axis = new LinuxJoystickPOV(Axis.POV, hatBits[2], hatBits[3]);
                device.registerPOV((LinuxJoystickPOV) axis);
                components.add(axis);
            } else if (axisMap[i] == NativeDefinitions.ABS_HAT2X) {
                hatBits[4] = axis;
            } else if (axisMap[i] == NativeDefinitions.ABS_HAT2Y) {
                hatBits[5] = axis;
                axis = new LinuxJoystickPOV(Axis.POV, hatBits[4], hatBits[5]);
                device.registerPOV((LinuxJoystickPOV) axis);
                components.add(axis);
            } else {
                components.add(axis);
            }
        }

        return new LinuxJoystickAbstractController(device, components.toArray(new Component[components.size()]), new Controller[]{}, new Rumbler[]{});
    }

    private static File[] enumerateJoystickDeviceFiles(final String dev_path) {
        final File dev = new File(dev_path);
        return listFilesPrivileged(dev, (dir, name) -> name.startsWith("js"));
    }

    private static String getAbsolutePathPrivileged(final File file) {
        return AccessController.doPrivileged((PrivilegedAction<String>) file::getAbsolutePath);
    }

    private static File[] listFilesPrivileged(final File dir, final FilenameFilter filter) {
        return AccessController.doPrivileged((PrivilegedAction<File[]>) () -> {
            File[] files = dir.listFiles(filter);
            if (files != null) {
                Arrays.sort(files, Comparator.comparing(f -> (f).getName()));
            }
            return files;
        });
    }

    /** Returns a list of all controllers available to this environment,
     * or an empty array if there are no controllers in this environment.
     * @return Returns a list of all controllers available to this environment,
     * or an empty array if there are no controllers in this environment.
     */
    public final Controller[] getControllers() {
        return controllers;
    }

    private Controller[] enumerateControllers() {
        List<Controller> controllers = new ArrayList<>();
        List<Controller> eventControllers = new ArrayList<>();
        List<Controller> jsControllers = new ArrayList<>();
        enumerateEventControllers(eventControllers);
        enumerateJoystickControllers(jsControllers);

        for (int i = 0; i < eventControllers.size(); i++) {
            for (int j = 0; j < jsControllers.size(); j++) {
                Controller evController = eventControllers.get(i);
                Controller jsController = jsControllers.get(j);

                // compare
                // Check if the nodes have the same name
                if (evController.getName().equals(jsController.getName())) {
                    // Check they have the same component count
                    Component[] evComponents = evController.getComponents();
                    Component[] jsComponents = jsController.getComponents();
                    if (evComponents.length == jsComponents.length) {
                        boolean foundADifference = false;
                        // check the component pairs are of the same type
                        for (int k = 0; k < evComponents.length; k++) {
                            // Check the type of the component is the same
                            if (!(evComponents[k].getIdentifier() == jsComponents[k].getIdentifier())) {
                                foundADifference = true;
                            }
                        }

                        if (!foundADifference) {
                            controllers.add(new LinuxCombinedController((LinuxAbstractController) eventControllers.remove(i), (LinuxJoystickAbstractController) jsControllers.remove(j)));
                            i--;
//							j--;
                            break;
                        }
                    }
                }
            }
        }
        controllers.addAll(eventControllers);
        controllers.addAll(jsControllers);

        Controller[] controllers_array = new Controller[controllers.size()];
        controllers.toArray(controllers_array);
        return controllers_array;
    }

    private void enumerateJoystickControllers(List<Controller> controllers) {
        File[] joystick_device_files = enumerateJoystickDeviceFiles("/dev/input");
        if (joystick_device_files == null || joystick_device_files.length == 0) {
            joystick_device_files = enumerateJoystickDeviceFiles("/dev");
            if (joystick_device_files == null)
                return;
        }
        for (File event_file : joystick_device_files) {
            try {
                String path = getAbsolutePathPrivileged(event_file);
                LinuxJoystickDevice device = new LinuxJoystickDevice(path);
                Controller controller = createJoystickFromJoystickDevice(device);
                if (controller != null) {
                    controllers.add(controller);
                    devices.add(device);
                } else
                    device.close();
            } catch (IOException e) {
                logln("Failed to open device (" + event_file + "): " + e.getMessage());
            }
        }
    }

    private void enumerateEventControllers(List<Controller> controllers) {
        final File dev = new File("/dev/input");
        File[] event_device_files = listFilesPrivileged(dev, (dir, name) -> name.startsWith("event"));
        if (event_device_files == null)
            return;
        for (File event_file : event_device_files) {
            try {
                String path = getAbsolutePathPrivileged(event_file);
                LinuxEventDevice device = new LinuxEventDevice(path);
                try {
                    Controller controller = createControllerFromDevice(device);
                    if (controller != null) {
                        controllers.add(controller);
                        devices.add(device);
                    } else
                        device.close();
                } catch (IOException e) {
                    logln("Failed to create Controller: " + e.getMessage());
                    device.close();
                }
            } catch (IOException e) {
                logln("Failed to open device (" + event_file + "): " + e.getMessage());
            }
        }
    }

    public boolean isSupported() {
        return supported;
    }

    private final class ShutdownHook extends Thread {
        public final void run() {
            for (LinuxDevice device : devices) {
                try {
                    device.close();
                } catch (IOException e) {
                    logln("Failed to close device: " + e.getMessage());
                }
            }
        }
    }
}
