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

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elias
 * @version 1.0
 */
final class DataQueue<T> {
    private final T[] elements;
    private int position;
    private int limit;

    public DataQueue(int size, Class<T> element_type) {
        Object[] temp = new Object[size];
        for (int i = 0; i < temp.length; i++) {
            try {
                temp[i] = element_type.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        elements = (T[]) temp;
        clear();
    }

    private void clear() {
        position = 0;
        limit = elements.length;
    }

    public final int position() {
        return position;
    }

    public final int limit() {
        return limit;
    }

    private T get(int index) {
        assert index < limit;
        return elements[index];
    }

    @Nullable
    public final T get() {
        if (!hasRemaining())
            return null;
        return get(position++);
    }

    public final void compact() {
        int index = 0;
        while (hasRemaining()) {
            swap(position, index);
            position++;
            index++;
        }
        position = index;
        limit = elements.length;
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    public final void flip() {
        limit = position;
        position = 0;
    }

    public final boolean hasRemaining() {
        return remaining() > 0;
    }

    public final int remaining() {
        return limit - position;
    }

    public final void position(int position) {
        this.position = position;
    }

    public final T[] getElements() {
        return elements;
    }
}
