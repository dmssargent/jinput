package net.java.games.input.example;

import net.java.games.input.*;

/**
 * This class shows how to use the event queue system in JInput. It will show
 * how to get the controllers, how to get the event queue for a controller, and
 * how to read and process events from the queue.
 *
 * @author Endolf
 */
class ReadAllEvents {

    private ReadAllEvents() {
        while (true) {
            /* Get the available controllers */
            Controller[] controllers = ControllerEnvironment
                    .getDefaultEnvironment().getControllers();
            if (controllers.length == 0) {
                System.out.println("Found no controllers.");
                System.exit(0);
            }

            for (Controller controller : controllers) {
                /* Remember to poll each one */
                controller.poll();

				/* Get the controllers event queue */
                EventQueue queue = controller.getEventQueue();

				/* Create an event object for the underlying plugin to populate */
                Event event = new Event();

				/* For each object in the queue */
                while (queue.getNextEvent(event)) {

					/*
                     * Create a strug buffer and put in it, the controller name,
					 * the time stamp of the event, the name of the component
					 * that changed and the new value.
					 *
					 * Note that the timestamp is a relative thing, not
					 * absolute, we can tell what order events happened in
					 * across controllers this way. We can not use it to tell
					 * exactly *when* an event happened just the order.
					 */
                    StringBuilder buffer = new StringBuilder(controller
                            .getName());
                    buffer.append(" at ");
                    buffer.append(event.getNanos()).append(", ");
                    Component comp = event.getComponent();
                    buffer.append(comp.getName()).append(" changed to ");
                    float value = event.getValue();

					/*
					 * Check the type of the component and display an
					 * appropriate value
					 */
                    if (comp.isAnalog()) {
                        buffer.append(value);
                    } else {
                        if (value == 1.0f) {
                            buffer.append("On");
                        } else {
                            buffer.append("Off");
                        }
                    }
                    System.out.println(buffer.toString());
                }
            }

			/*
			 * Sleep for 20 milliseconds, in here only so the example doesn't
			 * thrash the system.
			 */
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ReadAllEvents();
    }
}
