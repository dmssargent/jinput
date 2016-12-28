/*
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
import net.java.games.input.Controller.PortType;
import net.java.games.input.Controller.Type;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author elias
 */
final class LinuxEventDevice implements LinuxDevice {
    private final Map<LinuxAxisDescriptor, LinuxComponent> component_map = new HashMap<>();
    private final Rumbler[] rumblers;
    private final long fd;
    private final String name;
    private final LinuxInputID input_id;
    private final List components;
    private final Type type;
    /* Access to the key_states array could be synchronized, but
     * it doesn't hurt to have multiple threads read/write from/to it
     */
    private final byte[] key_states = new byte[NativeDefinitions.KEY_MAX / 8 + 1];
    /* Closed state variable that protects the validity of the file descriptor.
     *  Access to the closed state must be synchronized
     */
    private boolean closed;

    public LinuxEventDevice(String filename) throws IOException {
        long fd;
        boolean detect_rumblers = true;
        try {
            fd = nOpen(filename, true);
        } catch (IOException e) {
            fd = nOpen(filename, false);
            detect_rumblers = false;
        }
        this.fd = fd;
        try {
            this.name = getDeviceName();
            this.input_id = getDeviceInputID();
            this.components = getDeviceComponents();
            if (detect_rumblers)
                this.rumblers = enumerateRumblers();
            else
                this.rumblers = new Rumbler[]{};
            this.type = guessType();
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    private static native long nOpen(String filename, boolean rw) throws IOException;

    private static int countComponents(List components, Class id_type, boolean relative) {
        int count = 0;
        for (Object component1 : components) {
            LinuxEventComponent component = (LinuxEventComponent) component1;
            if (id_type.isInstance(component.getIdentifier()) && relative == component.isRelative())
                count++;
        }
        return count;
    }

    private static native int nUploadRumbleEffect(long fd, int id, int direction, int trigger_button, int trigger_interval, int replay_length, int replay_delay, int strong_magnitude, int weak_magnitude) throws IOException;

    private static native int nUploadConstantEffect(long fd, int id, int direction, int trigger_button, int trigger_interval, int replay_length, int replay_delay, int constant_level, int constant_env_attack_length, int constant_env_attack_level, int constant_env_fade_length, int constant_env_fade_level) throws IOException;

    private static native void nEraseEffect(long fd, int ff_id) throws IOException;

    private static native void nWriteEvent(long fd, int type, int code, int value) throws IOException;

    private static native LinuxInputID nGetInputID(long fd) throws IOException;

    private static native int nGetNumEffects(long fd) throws IOException;

    private static native int nGetVersion(long fd) throws IOException;

    private static native boolean nGetNextEvent(long fd, LinuxEvent linux_event) throws IOException;

    private static native void nGetAbsInfo(long fd, int abs_axis, LinuxAbsInfo abs_info) throws IOException;

    private static native void nGetBits(long fd, int ev_type, byte[] evtype_bits) throws IOException;

    private static native void nGetDeviceUsageBits(long fd, byte[] type_bits) throws IOException;

    private static native void nGetKeyStates(long fd, byte[] states) throws IOException;

    @Contract(pure = true)
    private static boolean isBitSet(byte[] bits, int bit) {
        return (bits[bit / 8] & (1 << (bit % 8))) != 0;
    }

    private static native String nGetName(long fd) throws IOException;

    private static native void nClose(long fd) throws IOException;

    @Contract(pure = true)
    public final Type getType() {
        return type;
    }

    private Type guessType() throws IOException {
        Type type_from_usages = guessTypeFromUsages();
        if (type_from_usages == Type.UNKNOWN)
            return guessTypeFromComponents();
        else
            return type_from_usages;
    }

    private Type guessTypeFromUsages() throws IOException {
        byte[] usage_bits = getDeviceUsageBits();
        if (isBitSet(usage_bits, NativeDefinitions.USAGE_MOUSE))
            return Type.MOUSE;
        else if (isBitSet(usage_bits, NativeDefinitions.USAGE_KEYBOARD))
            return Type.KEYBOARD;
        else if (isBitSet(usage_bits, NativeDefinitions.USAGE_GAMEPAD))
            return Type.GAMEPAD;
        else if (isBitSet(usage_bits, NativeDefinitions.USAGE_JOYSTICK))
            return Type.STICK;
        else
            return Type.UNKNOWN;
    }

    @Nullable
    private Type guessTypeFromComponents() throws IOException {
        List components = getComponents();
        if (components.size() == 0)
            return Type.UNKNOWN;
        int num_rel_axes = countComponents(components, Axis.class, true);
        int num_abs_axes = countComponents(components, Axis.class, false);
        int mouse_traits = 0;
        int keyboard_traits = 0;
        int joystick_traits = 0;
        int gamepad_traits = 0;
        if (name.toLowerCase().contains("mouse"))
            mouse_traits++;
        if (name.toLowerCase().contains("keyboard"))
            keyboard_traits++;
        if (name.toLowerCase().contains("joystick"))
            joystick_traits++;
        if (name.toLowerCase().contains("gamepad"))
            gamepad_traits++;
        int num_keyboard_button_traits = 0;
        int num_mouse_button_traits = 0;
        int num_joystick_button_traits = 0;
        int num_gamepad_button_traits = 0;
        // count button traits
        for (Object component1 : components) {
            LinuxEventComponent component = (LinuxEventComponent) component1;
            if (component.getButtonTrait() == Type.MOUSE)
                num_mouse_button_traits++;
            else if (component.getButtonTrait() == Type.KEYBOARD)
                num_keyboard_button_traits++;
            else if (component.getButtonTrait() == Type.GAMEPAD)
                num_gamepad_button_traits++;
            else if (component.getButtonTrait() == Type.STICK)
                num_joystick_button_traits++;
        }
        if ((num_mouse_button_traits >= num_keyboard_button_traits) && (num_mouse_button_traits >= num_joystick_button_traits) && (num_mouse_button_traits >= num_gamepad_button_traits)) {
            mouse_traits++;
        } else if ((num_keyboard_button_traits >= num_mouse_button_traits) && (num_keyboard_button_traits >= num_joystick_button_traits) && (num_keyboard_button_traits >= num_gamepad_button_traits)) {
            keyboard_traits++;
        } else if ((num_joystick_button_traits >= num_keyboard_button_traits) && (num_joystick_button_traits >= num_mouse_button_traits) && (num_joystick_button_traits >= num_gamepad_button_traits)) {
            joystick_traits++;
        } else if ((num_gamepad_button_traits >= num_keyboard_button_traits) && (num_gamepad_button_traits >= num_mouse_button_traits) && (num_gamepad_button_traits >= num_joystick_button_traits)) {
            gamepad_traits++;
        }
        if (num_rel_axes >= 2) {
            mouse_traits++;
        }
        if (num_abs_axes >= 2) {
            joystick_traits++;
            gamepad_traits++;
        }

        if ((mouse_traits >= keyboard_traits) && (mouse_traits >= joystick_traits) && (mouse_traits >= gamepad_traits)) {
            return Type.MOUSE;
        } else if ((keyboard_traits >= mouse_traits) && (keyboard_traits >= joystick_traits) && (keyboard_traits >= gamepad_traits)) {
            return Type.KEYBOARD;
        } else if ((joystick_traits >= mouse_traits) && (joystick_traits >= keyboard_traits) && (joystick_traits >= gamepad_traits)) {
            return Type.STICK;
        } else if ((gamepad_traits >= mouse_traits) && (gamepad_traits >= keyboard_traits) && (gamepad_traits >= joystick_traits)) {
            return Type.GAMEPAD;
        } else
            return null;
    }

    @NotNull
    private Rumbler[] enumerateRumblers() {
        List<LinuxRumbleFF> rumblers = new ArrayList<>();
        try {
            int num_effects = getNumEffects();
            if (num_effects <= 0)
                return rumblers.toArray(new Rumbler[rumblers.size()]);
            byte[] ff_bits = getForceFeedbackBits();
            if (isBitSet(ff_bits, NativeDefinitions.FF_RUMBLE) && num_effects > rumblers.size()) {
                rumblers.add(new LinuxRumbleFF(this));
            }
        } catch (IOException e) {
            ControllerEnvironment.logln("Failed to enumerate rumblers: " + e.getMessage());
        }
        return rumblers.toArray(new Rumbler[rumblers.size()]);
    }

    @Contract(pure = true)
    public final Rumbler[] getRumblers() {
        return rumblers;
    }

    public final synchronized int uploadRumbleEffect(int id, int trigger_button, int direction, int trigger_interval, int replay_length, int replay_delay, int strong_magnitude, int weak_magnitude) throws IOException {
        checkClosed();
        return nUploadRumbleEffect(fd, id, direction, trigger_button, trigger_interval, replay_length, replay_delay, strong_magnitude, weak_magnitude);
    }

    public final synchronized int uploadConstantEffect(int id, int trigger_button, int direction, int trigger_interval, int replay_length, int replay_delay, int constant_level, int constant_env_attack_length, int constant_env_attack_level, int constant_env_fade_length, int constant_env_fade_level) throws IOException {
        checkClosed();
        return nUploadConstantEffect(fd, id, direction, trigger_button, trigger_interval, replay_length, replay_delay, constant_level, constant_env_attack_length, constant_env_attack_level, constant_env_fade_length, constant_env_fade_level);
    }

    final void eraseEffect(int id) throws IOException {
        nEraseEffect(fd, id);
    }

    public final synchronized void writeEvent(int type, int code, int value) throws IOException {
        checkClosed();
        nWriteEvent(fd, type, code, value);
    }

    public final void registerComponent(LinuxAxisDescriptor desc, LinuxComponent component) {
        component_map.put(desc, component);
    }

    public final LinuxComponent mapDescriptor(LinuxAxisDescriptor desc) {
        return component_map.get(desc);
    }

    @Contract(pure = true)
    public final PortType getPortType() throws IOException {
        return input_id.getPortType();
    }

    @Contract(pure = true)
    public final LinuxInputID getInputID() {
        return input_id;
    }

    private LinuxInputID getDeviceInputID() throws IOException {
        return nGetInputID(fd);
    }

    private int getNumEffects() throws IOException {
        return nGetNumEffects(fd);
    }

    private int getVersion() throws IOException {
        return nGetVersion(fd);
    }

    public final synchronized boolean getNextEvent(LinuxEvent linux_event) throws IOException {
        checkClosed();
        return nGetNextEvent(fd, linux_event);
    }

    public final synchronized void getAbsInfo(int abs_axis, LinuxAbsInfo abs_info) throws IOException {
        checkClosed();
        nGetAbsInfo(fd, abs_axis, abs_info);
    }

    private void addKeys(List<LinuxEventComponent> components) throws IOException {
        byte[] bits = getKeysBits();
        for (int i = 0; i < bits.length * 8; i++) {
            if (isBitSet(bits, i)) {
                Identifier id = LinuxNativeTypesMap.getButtonID(i);
                components.add(new LinuxEventComponent(this, id, false, NativeDefinitions.EV_KEY, i));
            }
        }
    }

    private void addAbsoluteAxes(List<LinuxEventComponent> components) throws IOException {
        byte[] bits = getAbsoluteAxesBits();
        for (int i = 0; i < bits.length * 8; i++) {
            if (isBitSet(bits, i)) {
                Identifier id = LinuxNativeTypesMap.getAbsAxisID(i);
                components.add(new LinuxEventComponent(this, id, false, NativeDefinitions.EV_ABS, i));
            }
        }
    }

    private void addRelativeAxes(List<LinuxEventComponent> components) throws IOException {
        byte[] bits = getRelativeAxesBits();
        for (int i = 0; i < bits.length * 8; i++) {
            if (isBitSet(bits, i)) {
                Identifier id = LinuxNativeTypesMap.getRelAxisID(i);
                components.add(new LinuxEventComponent(this, id, true, NativeDefinitions.EV_REL, i));
            }
        }
    }

    @Contract(pure = true)
    public final List getComponents() {
        return components;
    }

    private List getDeviceComponents() throws IOException {
        List<LinuxEventComponent> components = new ArrayList<>();
        byte[] evtype_bits = getEventTypeBits();
        if (isBitSet(evtype_bits, NativeDefinitions.EV_KEY))
            addKeys(components);
        if (isBitSet(evtype_bits, NativeDefinitions.EV_ABS))
            addAbsoluteAxes(components);
        if (isBitSet(evtype_bits, NativeDefinitions.EV_REL))
            addRelativeAxes(components);
        return components;
    }

    private byte[] getForceFeedbackBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.FF_MAX / 8 + 1];
        nGetBits(fd, NativeDefinitions.EV_FF, bits);
        return bits;
    }

    private byte[] getKeysBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.KEY_MAX / 8 + 1];
        nGetBits(fd, NativeDefinitions.EV_KEY, bits);
        return bits;
    }

    private byte[] getAbsoluteAxesBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.ABS_MAX / 8 + 1];
        nGetBits(fd, NativeDefinitions.EV_ABS, bits);
        return bits;
    }

    private byte[] getRelativeAxesBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.REL_MAX / 8 + 1];
        nGetBits(fd, NativeDefinitions.EV_REL, bits);
        return bits;
    }

    private byte[] getEventTypeBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.EV_MAX / 8 + 1];
        nGetBits(fd, 0, bits);
        return bits;
    }

    private byte[] getDeviceUsageBits() throws IOException {
        byte[] bits = new byte[NativeDefinitions.USAGE_MAX / 8 + 1];
        if (getVersion() >= 0x010001) {
            nGetDeviceUsageBits(fd, bits);
        }
        return bits;
    }

    public final synchronized void pollKeyStates() throws IOException {
        nGetKeyStates(fd, key_states);
    }

    @Contract(pure = true)
    public final boolean isKeySet(int bit) {
        return isBitSet(key_states, bit);
    }

    @Contract(pure = true)
    public final String getName() {
        return name;
    }

    private String getDeviceName() throws IOException {
        return nGetName(fd);
    }

    @Override
    public synchronized final void close() throws IOException {
        if (closed) return;
        closed = true;
        LinuxEnvironmentPlugin.execute(new LinuxDeviceTask<Object>() {
            @Override
            protected final Object execute() throws IOException {
                nClose(fd);
                return null;
            }
        });
    }

    private void checkClosed() throws IOException {
        if (closed)
            throw new IOException("Device is closed");
    }

    @Override
    protected void finalize() throws IOException {
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        close();
    }
}
