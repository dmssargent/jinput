/*
 * %W% %E%
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*****************************************************************************
 * Copyright (c) 2003 Sun Microsystems, Inc.  All Rights Reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * - Redistribution of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <p>
 * - Redistribution in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materails provided with the distribution.
 * <p>
 * Neither the name Sun Microsystems, Inc. or the names of the contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * <p>
 * This software is provided "AS IS," without a warranty of any kind.
 * ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANT OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMEN, ARE HEREBY EXCLUDED.  SUN MICROSYSTEMS, INC. ("SUN") AND
 * ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS
 * A RESULT OF USING, MODIFYING OR DESTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.  IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES.  HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OUR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * <p>
 * You acknowledge that this software is not designed or intended for us in
 * the design, construction, operation or maintenance of any nuclear facility
 *****************************************************************************/
package net.java.games.input;

import net.java.games.input.Component.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * An AbstractController is a skeleton implementation of a controller that
 * contains a fixed number of axes, controllers, and rumblers.
 */
public abstract class AbstractController implements Controller {
    final static int EVENT_QUEUE_DEPTH = 32;

    private final static Event event = new Event();

    /**
     * Human-readable name for this Controller
     */
    private final String name;

    /**
     * Array of components
     */
    @NotNull
    private final Component[] components;

    /**
     * Array of child controllers
     */
    private final Controller[] children;

    /**
     * Array of rumblers
     */
    private final Rumbler[] rumblers;

    /**
     * Map from Component.Identifiers to Components
     */
    private final Map<Identifier, Component> id_to_components = new HashMap<>();

    @NotNull
    private EventQueue event_queue = new EventQueue(EVENT_QUEUE_DEPTH);

    /**
     * Protected constructor for a controller containing the specified
     * axes, child controllers, and rumblers
     *
     * @param name       name for the controller
     * @param components components for the controller
     * @param children   child controllers for the controller
     * @param rumblers   rumblers for the controller
     */
    AbstractController(String name, @NotNull Component[] components, Controller[] children, Rumbler[] rumblers) {
        this.name = name;
        this.components = components;
        this.children = children;
        this.rumblers = rumblers;
        // process from last to first to let earlier listed Components get higher priority
        for (int i = components.length - 1; i >= 0; i--) {
            id_to_components.put(components[i].getIdentifier(), components[i]);
        }
    }

    /**
     * Returns the controllers connected to make up this controller, or
     * an empty array if this controller contains no child controllers.
     * The objects in the array are returned in order of assignment priority
     * (primary stick, secondary buttons, etc.).
     */
    @Override
    @Contract(pure = true)
    public final Controller[] getControllers() {
        return children;
    }

    /**
     * Returns the components on this controller, in order of assignment priority.
     * For example, the button controller on a mouse returns an array containing
     * the primary or leftmost mouse button, followed by the secondary or
     * rightmost mouse button (if present), followed by the middle mouse button
     * (if present).
     * The array returned is an empty array if this controller contains no components
     * (such as a logical grouping of child controllers).
     */
    @Contract(pure = true)
    @NotNull
    @Override
    public final Component[] getComponents() {
        return components;
    }

    /**
     * Returns a single component based on its identifier, or null
     * if no component with the specified type could be found.
     */
    @NotNull
    @Override
    public final Component getComponent(Identifier id) {
        return id_to_components.get(id);
    }

    /**
     * Returns the rumblers for sending feedback to this controller, or an
     * empty array if there are no rumblers on this controller.
     */
    @Contract(pure = true)
    @Override
    public final Rumbler[] getRumblers() {
        return rumblers;
    }

    /**
     * Returns the port type for this Controller.
     *
     * @return PortType.UNKNOWN by default, can be overridden
     */
    @Override
    public PortType getPortType() {
        return PortType.UNKNOWN;
    }

    /**
     * Returns the zero-based port number for this Controller.
     *
     * @return 0 by default, can be overridden
     */
    @Override
    public int getPortNumber() {
        return 0;
    }

    /**
     * Returns a human-readable name for this Controller.
     */
    @Override
    public final String getName() {
        return name;
    }

    /**
     * Returns a non-localized string description of this controller.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns the type of the Controller.
     */
    @Override
    public Type getType() {
        return Type.UNKNOWN;
    }

    /**
     * Creates a new EventQueue. Events in old queue are lost.
     */
    @Override
    public final void setEventQueueSize(int size) {
        try {
            setDeviceEventQueueSize(size);
            event_queue = new EventQueue(size);
        } catch (IOException e) {
            ControllerEnvironment.logln("Failed to create new event queue of size " + size + ": " + e);
        }
    }

    /**
     * Plugins override this method to adjust their internal event queue size
     */
    void setDeviceEventQueueSize(int size) throws IOException {
    }

    @NotNull
    @Override
    public final EventQueue getEventQueue() {
        return event_queue;
    }

    protected abstract boolean getNextDeviceEvent(Event event) throws IOException;

    void pollDevice() throws IOException {
    }

    /* poll() is synchronized to protect the static event */
    @Override
    public synchronized boolean poll() {
        Component[] components = getComponents();
        try {
            pollDevice();
            for (Component component1 : components) {
                AbstractComponent component = (AbstractComponent) component1;
                if (component.isRelative()) {
                    component.setPollData(0);
                } else {
                    // Let the component poll itself lazily
                    component.resetHasPolled();
                }
            }
            while (getNextDeviceEvent(event)) {
                AbstractComponent component = (AbstractComponent) event.getComponent();
                float value = event.getValue();
                if (component.isRelative()) {
                    if (value == 0)
                        continue;
                    component.setPollData(component.getPollData() + value);
                } else {
                    if (value == component.getEventValue())
                        continue;
                    component.setEventValue(value);
                }
                if (!event_queue.isFull())
                    event_queue.add(event);
            }
            return true;
        } catch (IOException e) {
            ControllerEnvironment.logln("Failed to poll device: " + e.getMessage());
            return false;
        }
    }

} // class AbstractController
