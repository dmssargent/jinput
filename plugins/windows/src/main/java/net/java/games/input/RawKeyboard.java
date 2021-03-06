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

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author elias
 * @version 1.0
 */
final class RawKeyboard extends Keyboard {
    private final RawKeyboardEvent raw_event = new RawKeyboardEvent();
    private final RawDevice device;

    RawKeyboard(String name, RawDevice device, Controller[] children, Rumbler[] rumblers) throws IOException {
        super(name, createKeyboardComponents(device), children, rumblers);
        this.device = device;
    }

    private static Component[] createKeyboardComponents(RawDevice device) {
        List<Key> components = new ArrayList<>();
        Field[] vkey_fields = RawIdentifierMap.class.getFields();
        for (Field vkey_field : vkey_fields) {
            try {
                if (Modifier.isStatic(vkey_field.getModifiers()) && vkey_field.getType() == int.class) {
                    int vkey_code = vkey_field.getInt(null);
                    Identifier.Key key_id = RawIdentifierMap.mapVKey(vkey_code);
                    if (key_id != Identifier.Key.UNKNOWN)
                        components.add(new Key(device, vkey_code, key_id));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return components.toArray(new Component[components.size()]);
    }

    @Override
    protected final synchronized boolean getNextDeviceEvent(Event event) throws IOException {
        while (true) {
            if (!device.getNextKeyboardEvent(raw_event))
                return false;
            int vkey = raw_event.getVKey();
            Identifier.Key key_id = RawIdentifierMap.mapVKey(vkey);
            Component key = getComponent(key_id);
            if (key == null) {
                continue;
            }
            int message = raw_event.getMessage();
            if (message == RawDevice.WM_KEYDOWN || message == RawDevice.WM_SYSKEYDOWN) {
                event.set(key, 1, raw_event.getNanos());
                return true;
            } else if (message == RawDevice.WM_KEYUP || message == RawDevice.WM_SYSKEYUP) {
                event.set(key, 0, raw_event.getNanos());
                return true;
            }
        }
    }

    @Override
    public final void pollDevice() throws IOException {
        device.pollKeyboard();
    }

    @Override
    protected final void setDeviceEventQueueSize(int size) throws IOException {
        device.setBufferSize(size);
    }

    final static class Key extends AbstractComponent {
        private final RawDevice device;
        private final int vkey_code;

        public Key(RawDevice device, int vkey_code, Identifier.Key key_id) {
            super(key_id.getName(), key_id);
            this.device = device;
            this.vkey_code = vkey_code;
        }

        @Override
        protected final float poll() throws IOException {
            return device.isKeyDown(vkey_code) ? 1f : 0f;
        }

        @Override
        public final boolean isAnalog() {
            return false;
        }

        @Override
        public final boolean isRelative() {
            return false;
        }
    }
}
