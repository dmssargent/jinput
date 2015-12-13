/*
 * RumbleTest.java
 *
 * Created on 01 December 2003, 23:02
 */
package net.java.games.input.test;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Rumbler;
import net.java.games.input.Version;

/**
 * @author Jeremy
 */
class RumbleTest {

    /**
     * Creates a new instance of RumbleTest
     */
    private RumbleTest() {
        ControllerEnvironment ca = ControllerEnvironment.getDefaultEnvironment();
        System.out.println("JInput version: " + Version.getVersion());
        Controller[] controllers = ca.getControllers();
        for (Controller controller : controllers) {
            System.out.println("Scanning " + controller.getName());
            Rumbler[] rumblers = controller.getRumblers();
            System.out.println("Found " + rumblers.length + " rumblers");
            for (Rumbler rumbler : rumblers) {
                System.out.println("Rumbler " + rumbler.getAxisName() + " on axis " + rumbler.getAxisIdentifier());
                System.out.println("Rumbling with intensity: " + 0.5f);
                rumbler.rumble(0.5f);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Rumbling with intensity: " + 1.0f);
                rumbler.rumble(1f);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Fading rumble to -1");
                for (float k = 1.0f; k > -1.0f; ) {
                    long startTime = System.currentTimeMillis();
                    rumbler.rumble(k);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ignored) {
                    }
                    k -= ((float) (System.currentTimeMillis() - startTime)) / 1000f;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Rumbling with intensity: " + 0.0f);
                rumbler.rumble(0f);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        System.exit(0);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new RumbleTest();
    }

}
