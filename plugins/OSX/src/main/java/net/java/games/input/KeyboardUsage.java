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

import org.jetbrains.annotations.Contract;

/** Mapping from Keyboard HID usages to Component.Identifier.Key
 * @author elias
 * @version 1.0
 */
final class KeyboardUsage implements Usage {
    private final static KeyboardUsage[] map = new KeyboardUsage[0xFF];

    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage ERRORROLLOVER = new KeyboardUsage(0x01);	/* ErrorRollOver */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage POSTFAIL = new KeyboardUsage(0x02);	/* POSTFail */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage ERRORUNDEFINED = new KeyboardUsage(0x03);	/* ErrorUndefined */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage A = new KeyboardUsage(Component.Identifier.Key.A, 0x04);	/* a or A */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage B = new KeyboardUsage(Component.Identifier.Key.B, 0x05);	/* b or B */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage C = new KeyboardUsage(Component.Identifier.Key.C, 0x06);	/* c or C */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage D = new KeyboardUsage(Component.Identifier.Key.D, 0x07);	/* d or D */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage E = new KeyboardUsage(Component.Identifier.Key.E, 0x08);	/* e or E */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F = new KeyboardUsage(Component.Identifier.Key.F, 0x09);	/* f or F */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage G = new KeyboardUsage(Component.Identifier.Key.G, 0x0A);	/* g or G */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage H = new KeyboardUsage(Component.Identifier.Key.H, 0x0B);	/* h or H */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage I = new KeyboardUsage(Component.Identifier.Key.I, 0x0C);	/* i or I */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage J = new KeyboardUsage(Component.Identifier.Key.J, 0x0D);	/* j or J */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage K = new KeyboardUsage(Component.Identifier.Key.K, 0x0E);	/* k or K */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage L = new KeyboardUsage(Component.Identifier.Key.L, 0x0F);	/* l or L */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage M = new KeyboardUsage(Component.Identifier.Key.M, 0x10);	/* m or M */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage N = new KeyboardUsage(Component.Identifier.Key.N, 0x11);	/* n or N */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage O = new KeyboardUsage(Component.Identifier.Key.O, 0x12);	/* o or O */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage P = new KeyboardUsage(Component.Identifier.Key.P, 0x13);	/* p or P */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage Q = new KeyboardUsage(Component.Identifier.Key.Q, 0x14);	/* q or Q */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage R = new KeyboardUsage(Component.Identifier.Key.R, 0x15);	/* r or R */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage S = new KeyboardUsage(Component.Identifier.Key.S, 0x16);	/* s or S */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage T = new KeyboardUsage(Component.Identifier.Key.T, 0x17);	/* t or T */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage U = new KeyboardUsage(Component.Identifier.Key.U, 0x18);	/* u or U */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage V = new KeyboardUsage(Component.Identifier.Key.V, 0x19);	/* v or V */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage W = new KeyboardUsage(Component.Identifier.Key.W, 0x1A);	/* w or W */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage X = new KeyboardUsage(Component.Identifier.Key.X, 0x1B);	/* x or X */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage Y = new KeyboardUsage(Component.Identifier.Key.Y, 0x1C);	/* y or Y */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage Z = new KeyboardUsage(Component.Identifier.Key.Z, 0x1D);	/* z or Z */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _1 = new KeyboardUsage(Component.Identifier.Key._1, 0x1E);	/* 1 or ! */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _2 = new KeyboardUsage(Component.Identifier.Key._2, 0x1F);	/* 2 or @ */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _3 = new KeyboardUsage(Component.Identifier.Key._3, 0x20);	/* 3 or # */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _4 = new KeyboardUsage(Component.Identifier.Key._4, 0x21);	/* 4 or $ */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _5 = new KeyboardUsage(Component.Identifier.Key._5, 0x22);	/* 5 or % */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _6 = new KeyboardUsage(Component.Identifier.Key._6, 0x23);	/* 6 or ^ */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _7 = new KeyboardUsage(Component.Identifier.Key._7, 0x24);	/* 7 or & */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _8 = new KeyboardUsage(Component.Identifier.Key._8, 0x25);	/* 8 or * */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _9 = new KeyboardUsage(Component.Identifier.Key._9, 0x26);	/* 9 or ( */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage _0 = new KeyboardUsage(Component.Identifier.Key._0, 0x27);	/* 0 or ) */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage ENTER = new KeyboardUsage(Component.Identifier.Key.RETURN, 0x28);	/* Return (Enter) */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage ESCAPE = new KeyboardUsage(Component.Identifier.Key.ESCAPE, 0x29);	/* Escape */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage BACKSPACE = new KeyboardUsage(Component.Identifier.Key.BACK, 0x2A);	/* Delete (Backspace) */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage TAB = new KeyboardUsage(Component.Identifier.Key.TAB, 0x2B);	/* Tab */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SPACEBAR = new KeyboardUsage(Component.Identifier.Key.SPACE, 0x2C);	/* Spacebar */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage HYPHEN = new KeyboardUsage(Component.Identifier.Key.MINUS, 0x2D);	/* - or _ */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage EQUALSIGN = new KeyboardUsage(Component.Identifier.Key.EQUALS, 0x2E);	/* = or + */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage OPENBRACKET = new KeyboardUsage(Component.Identifier.Key.LBRACKET, 0x2F);	/* [ or { */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CLOSEBRACKET = new KeyboardUsage(Component.Identifier.Key.RBRACKET, 0x30);	/* ] or } */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage BACKSLASH = new KeyboardUsage(Component.Identifier.Key.BACKSLASH, 0x31);	/* \ or | */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage NONUSPOUNT = new KeyboardUsage(Component.Identifier.Key.PERIOD, 0x32);	/* Non-US # or _ */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SEMICOLON = new KeyboardUsage(Component.Identifier.Key.SEMICOLON, 0x33);	/* ; or : */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage QUOTE = new KeyboardUsage(Component.Identifier.Key.APOSTROPHE, 0x34);	/* ' or " */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage TILDE = new KeyboardUsage(Component.Identifier.Key.GRAVE, 0x35);	/* Grave Accent and Tilde */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage COMMA = new KeyboardUsage(Component.Identifier.Key.COMMA, 0x36);	/* , or < */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PERIOD = new KeyboardUsage(Component.Identifier.Key.PERIOD, 0x37);	/* . or > */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SLASH = new KeyboardUsage(Component.Identifier.Key.SLASH, 0x38);	/* / or ? */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CAPSLOCK = new KeyboardUsage(Component.Identifier.Key.CAPITAL, 0x39);	/* Caps Lock */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F1 = new KeyboardUsage(Component.Identifier.Key.F1, 0x3A);	/* F1 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F2 = new KeyboardUsage(Component.Identifier.Key.F2, 0x3B);	/* F2 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F3 = new KeyboardUsage(Component.Identifier.Key.F3, 0x3C);	/* F3 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F4 = new KeyboardUsage(Component.Identifier.Key.F4, 0x3D);	/* F4 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F5 = new KeyboardUsage(Component.Identifier.Key.F5, 0x3E);	/* F5 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F6 = new KeyboardUsage(Component.Identifier.Key.F6, 0x3F);	/* F6 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F7 = new KeyboardUsage(Component.Identifier.Key.F7, 0x40);	/* F7 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F8 = new KeyboardUsage(Component.Identifier.Key.F8, 0x41);	/* F8 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F9 = new KeyboardUsage(Component.Identifier.Key.F9, 0x42);	/* F9 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F10 = new KeyboardUsage(Component.Identifier.Key.F10, 0x43);	/* F10 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F11 = new KeyboardUsage(Component.Identifier.Key.F11, 0x44);	/* F11 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F12 = new KeyboardUsage(Component.Identifier.Key.F12, 0x45);	/* F12 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PRINTSCREEN = new KeyboardUsage(Component.Identifier.Key.SYSRQ, 0x46);	/* PrintScreen */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SCROLLLOCK = new KeyboardUsage(Component.Identifier.Key.SCROLL, 0x47);	/* Scroll Lock */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PAUSE = new KeyboardUsage(Component.Identifier.Key.PAUSE, 0x48);	/* Pause */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INSERT = new KeyboardUsage(Component.Identifier.Key.INSERT, 0x49);	/* Insert */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage HOME = new KeyboardUsage(Component.Identifier.Key.HOME, 0x4A);	/* Home */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PAGEUP = new KeyboardUsage(Component.Identifier.Key.PAGEUP, 0x4B);	/* Page Up */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage DELETE = new KeyboardUsage(Component.Identifier.Key.DELETE, 0x4C);	/* Delete Forward */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage END = new KeyboardUsage(Component.Identifier.Key.END, 0x4D);	/* End */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PAGEDOWN = new KeyboardUsage(Component.Identifier.Key.PAGEDOWN, 0x4E);	/* Page Down */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RIGHTARROW = new KeyboardUsage(Component.Identifier.Key.RIGHT, 0x4F);	/* Right Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LEFTARROW = new KeyboardUsage(Component.Identifier.Key.LEFT, 0x50);	/* Left Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage DOWNARROW = new KeyboardUsage(Component.Identifier.Key.DOWN, 0x51);	/* Down Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage UPARROW = new KeyboardUsage(Component.Identifier.Key.UP, 0x52);	/* Up Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_NUMLOCK = new KeyboardUsage(Component.Identifier.Key.NUMLOCK, 0x53);	/* Keypad NumLock or Clear */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_SLASH = new KeyboardUsage(Component.Identifier.Key.DIVIDE, 0x54);	/* Keypad / */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_ASTERICK = new KeyboardUsage(0x55);	/* Keypad * */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_HYPHEN = new KeyboardUsage(Component.Identifier.Key.SUBTRACT, 0x56);	/* Keypad - */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_PLUS = new KeyboardUsage(Component.Identifier.Key.ADD, 0x57);	/* Keypad + */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_ENTER = new KeyboardUsage(Component.Identifier.Key.NUMPADENTER, 0x58);	/* Keypad Enter */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_1 = new KeyboardUsage(Component.Identifier.Key.NUMPAD1, 0x59);	/* Keypad 1 or End */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_2 = new KeyboardUsage(Component.Identifier.Key.NUMPAD2, 0x5A);	/* Keypad 2 or Down Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_3 = new KeyboardUsage(Component.Identifier.Key.NUMPAD3, 0x5B);	/* Keypad 3 or Page Down */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_4 = new KeyboardUsage(Component.Identifier.Key.NUMPAD4, 0x5C);	/* Keypad 4 or Left Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_5 = new KeyboardUsage(Component.Identifier.Key.NUMPAD5, 0x5D);	/* Keypad 5 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_6 = new KeyboardUsage(Component.Identifier.Key.NUMPAD6, 0x5E);	/* Keypad 6 or Right Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_7 = new KeyboardUsage(Component.Identifier.Key.NUMPAD7, 0x5F);	/* Keypad 7 or Home */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_8 = new KeyboardUsage(Component.Identifier.Key.NUMPAD8, 0x60);	/* Keypad 8 or Up Arrow */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_9 = new KeyboardUsage(Component.Identifier.Key.NUMPAD9, 0x61);	/* Keypad 9 or Page Up */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_0 = new KeyboardUsage(Component.Identifier.Key.NUMPAD0, 0x62);	/* Keypad 0 or Insert */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_PERIOD = new KeyboardUsage(Component.Identifier.Key.DECIMAL, 0x63);	/* Keypad . or Delete */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage NONUSBACKSLASH = new KeyboardUsage(Component.Identifier.Key.BACKSLASH, 0x64);	/* Non-US \ or | */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage APPLICATION = new KeyboardUsage(Component.Identifier.Key.APPS, 0x65);	/* Application */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage POWER = new KeyboardUsage(Component.Identifier.Key.POWER, 0x66);	/* Power */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_EQUALSIGN = new KeyboardUsage(Component.Identifier.Key.NUMPADEQUAL, 0x67);	/* Keypad = */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F13 = new KeyboardUsage(Component.Identifier.Key.F13, 0x68);	/* F13 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F14 = new KeyboardUsage(Component.Identifier.Key.F14, 0x69);	/* F14 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F15 = new KeyboardUsage(Component.Identifier.Key.F15, 0x6A);	/* F15 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F16 = new KeyboardUsage(0x6B);	/* F16 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F17 = new KeyboardUsage(0x6C);	/* F17 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F18 = new KeyboardUsage(0x6D);	/* F18 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F19 = new KeyboardUsage(0x6E);	/* F19 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F20 = new KeyboardUsage(0x6F);	/* F20 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F21 = new KeyboardUsage(0x70);	/* F21 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F22 = new KeyboardUsage(0x71);	/* F22 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F23 = new KeyboardUsage(0x72);	/* F23 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage F24 = new KeyboardUsage(0x73);	/* F24 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage EXECUTE = new KeyboardUsage(0x74);	/* Execute */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage HELP = new KeyboardUsage(0x75);	/* Help */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage MENU = new KeyboardUsage(0x76);	/* Menu */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SELECT = new KeyboardUsage(0x77);	/* Select */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage STOP = new KeyboardUsage(Component.Identifier.Key.STOP, 0x78);	/* Stop */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage AGAIN = new KeyboardUsage(0x79);	/* Again */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage UNDO = new KeyboardUsage(0x7A);	/* Undo */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CUT = new KeyboardUsage(0x7B);	/* Cut */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage COPY = new KeyboardUsage(0x7C);	/* Copy */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PASTE = new KeyboardUsage(0x7D);	/* Paste */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage FIND = new KeyboardUsage(0x7E);	/* Find */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage MUTE = new KeyboardUsage(0x7F);	/* Mute */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage VOLUMEUP = new KeyboardUsage(0x80);	/* Volume Up */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage VOLUMEDOWN = new KeyboardUsage(0x81);	/* Volume Down */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LOCKINGCAPSLOCK = new KeyboardUsage(Component.Identifier.Key.CAPITAL, 0x82);	/* Locking Caps Lock */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LOCKINGNUMLOCK = new KeyboardUsage(Component.Identifier.Key.NUMLOCK, 0x83);	/* Locking Num Lock */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LOCKINGSCROLLLOCK = new KeyboardUsage(Component.Identifier.Key.SCROLL, 0x84);	/* Locking Scroll Lock */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_COMMA = new KeyboardUsage(Component.Identifier.Key.COMMA, 0x85);	/* Keypad Comma */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage KEYPAD_EQUALSSIGNAS400 = new KeyboardUsage(0x86);	/* Keypad Equal Sign for AS/400 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL1 = new KeyboardUsage(0x87);	/* International1 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL2 = new KeyboardUsage(0x88);	/* International2 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL3 = new KeyboardUsage(0x89);	/* International3 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL4 = new KeyboardUsage(0x8A);	/* International4 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL5 = new KeyboardUsage(0x8B);	/* International5 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL6 = new KeyboardUsage(0x8C);	/* International6 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL7 = new KeyboardUsage(0x8D);	/* International7 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL8 = new KeyboardUsage(0x8E);	/* International8 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage INTERNATIONAL9 = new KeyboardUsage(0x8F);	/* International9 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG1 = new KeyboardUsage(0x90);	/* LANG1 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG2 = new KeyboardUsage(0x91);	/* LANG2 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG3 = new KeyboardUsage(0x92);	/* LANG3 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG4 = new KeyboardUsage(0x93);	/* LANG4 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG5 = new KeyboardUsage(0x94);	/* LANG5 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG6 = new KeyboardUsage(0x95);	/* LANG6 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG7 = new KeyboardUsage(0x96);	/* LANG7 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG8 = new KeyboardUsage(0x97);	/* LANG8 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LANG9 = new KeyboardUsage(0x98);	/* LANG9 */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage ALTERNATEERASE = new KeyboardUsage(0x99);	/* AlternateErase */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SYSREQORATTENTION = new KeyboardUsage(Component.Identifier.Key.SYSRQ, 0x9A);	/* SysReq/Attention */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CANCEL = new KeyboardUsage(0x9B);	/* Cancel */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CLEAR = new KeyboardUsage(0x9C);	/* Clear */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage PRIOR = new KeyboardUsage(Component.Identifier.Key.PAGEUP, 0x9D);	/* Prior */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RETURN = new KeyboardUsage(Component.Identifier.Key.RETURN, 0x9E);	/* Return */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage SEPARATOR = new KeyboardUsage(0x9F);	/* Separator */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage OUT = new KeyboardUsage(0xA0);	/* Out */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage OPER = new KeyboardUsage(0xA1);	/* Oper */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CLEARORAGAIN = new KeyboardUsage(0xA2);	/* Clear/Again */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage CRSELORPROPS = new KeyboardUsage(0xA3);	/* CrSel/Props */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage EXSEL = new KeyboardUsage(0xA4);	/* ExSel */
// --Commented out by Inspection START (11/29/2015 12:48 AM):
//	/* 0xA5-0xDF Reserved */
//	public static final KeyboardUsage LEFTCONTROL = new KeyboardUsage(Component.Identifier.Key.LCONTROL, 0xE0);	/* Left Control */
// --Commented out by Inspection STOP (11/29/2015 12:48 AM)
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LEFTSHIFT = new KeyboardUsage(Component.Identifier.Key.LSHIFT, 0xE1);	/* Left Shift */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LEFTALT = new KeyboardUsage(Component.Identifier.Key.LALT, 0xE2);	/* Left Alt */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage LEFTGUI = new KeyboardUsage(Component.Identifier.Key.LWIN, 0xE3);	/* Left GUI */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RIGHTCONTROL = new KeyboardUsage(Component.Identifier.Key.RCONTROL, 0xE4);	/* Right Control */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RIGHTSHIFT = new KeyboardUsage(Component.Identifier.Key.RSHIFT, 0xE5);	/* Right Shift */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RIGHTALT = new KeyboardUsage(Component.Identifier.Key.RALT, 0xE6);	/* Right Alt */
    // --Commented out by Inspection (11/29/2015 12:48 AM):public static final KeyboardUsage RIGHTGUI = new KeyboardUsage(Component.Identifier.Key.RWIN, 0xE7);	/* Right GUI */

    private final int usage;
    private final Component.Identifier.Key identifier;

    private KeyboardUsage(int usage) {
        this(Component.Identifier.Key.UNKNOWN, usage);
    }


    private KeyboardUsage(Component.Identifier.Key id, int usage) {
        this.identifier = id;
        this.usage = usage;
        map[usage] = this;
    }

    @Contract(pure = true)
    public static KeyboardUsage map(int usage) {
        if (usage < 0 || usage >= map.length)
            return null;
        return map[usage];
    }

    public final Component.Identifier.Key getIdentifier() {
        return identifier;
    }

    public final String toString() {
        return "KeyboardUsage (0x" + Integer.toHexString(usage) + ")";
    }
}
