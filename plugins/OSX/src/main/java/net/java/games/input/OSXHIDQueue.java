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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author elias
 * @version 1.0
 */
final class OSXHIDQueue {
    private final Map map = new HashMap();
    private final long queue_address;

    private boolean released;


    public OSXHIDQueue(long address, int queue_depth) throws IOException {
        this.queue_address = address;
        try {
            createQueue(queue_depth);
        } catch (IOException e) {
            release();
            throw e;
        }
    }

    private static native void nOpen(long queue_address, int queue_depth) throws IOException;

    private static native void nClose(long queue_address) throws IOException;

    private static native void nStart(long queue_address) throws IOException;

    private static native void nStop(long queue_address) throws IOException;

    private static native void nReleaseQueue(long queue_address) throws IOException;

    private static native void nAddElement(long queue_address, long cookie) throws IOException;

    private static native void nRemoveElement(long queue_address, long cookie) throws IOException;

    private static native boolean nGetNextEvent(long queue_address, OSXEvent event) throws IOException;

    public final synchronized void setQueueDepth(int queue_depth) throws IOException {
        checkReleased();
        stop();
        close();
        createQueue(queue_depth);
    }

    private void createQueue(int queue_depth) throws IOException {
        open(queue_depth);
        try {
            start();
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    public final OSXComponent mapEvent(OSXEvent event) {
        return (OSXComponent) map.get(event.getCookie());
    }

    private void open(int queue_depth) throws IOException {
        nOpen(queue_address, queue_depth);
    }

    private void close() throws IOException {
        nClose(queue_address);
    }

    private void start() throws IOException {
        nStart(queue_address);
    }

    private void stop() throws IOException {
        nStop(queue_address);
    }

    public final synchronized void release() throws IOException {
        if (!released) {
            released = true;
            try {
                stop();
                close();
            } finally {
                nReleaseQueue(queue_address);
            }
        }
    }

    public final void addElement(OSXHIDElement element, OSXComponent component) throws IOException {
        nAddElement(queue_address, element.getCookie());
        map.put(element.getCookie(), component);
    }

    public final void removeElement(OSXHIDElement element) throws IOException {
        nRemoveElement(queue_address, element.getCookie());
        map.remove(element.getCookie());
    }

    public final synchronized boolean getNextEvent(OSXEvent event) throws IOException {
        checkReleased();
        return nGetNextEvent(queue_address, event);
    }

    private void checkReleased() throws IOException {
        if (released)
            throw new IOException("Queue is released");
    }
}
