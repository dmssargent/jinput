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
import net.java.games.input.Component.Identifier.Key;

/**
 * @author elias
 * @version 1.0
 */
final class RawIdentifierMap {
    public final static int VK_LBUTTON = 0x01;
    public final static int VK_RBUTTON = 0x02;
    public final static int VK_CANCEL = 0x03;
    public final static int VK_MBUTTON = 0x04;    /* NOT contiguous with L & RBUTTON */

    public final static int VK_XBUTTON1 = 0x05;    /* NOT contiguous with L & RBUTTON */
    public final static int VK_XBUTTON2 = 0x06;    /* NOT contiguous with L & RBUTTON */

/*
 * 0x07 : unassigned
 */

    private final static int VK_BACK = 0x08;
    private final static int VK_TAB = 0x09;

/*
 * 0x0A - 0x0B : reserved
 */

    public final static int VK_CLEAR = 0x0C;
    private final static int VK_RETURN = 0x0D;

    private final static int VK_SHIFT = 0x10;
    private final static int VK_CONTROL = 0x11;
    private final static int VK_MENU = 0x12;
    private final static int VK_PAUSE = 0x13;
    private final static int VK_CAPITAL = 0x14;

    private final static int VK_KANA = 0x15;
    public final static int VK_HANGEUL = 0x15;  /* old name - should be here for compatibility */
    public final static int VK_HANGUL = 0x15;
    public final static int VK_JUNJA = 0x17;
    public final static int VK_FINAL = 0x18;
    public final static int VK_HANJA = 0x19;
    private final static int VK_KANJI = 0x19;

    private final static int VK_ESCAPE = 0x1B;

    private final static int VK_CONVERT = 0x1C;
    public final static int VK_NONCONVERT = 0x1D;
    public final static int VK_ACCEPT = 0x1E;
    public final static int VK_MODECHANGE = 0x1F;

    private final static int VK_SPACE = 0x20;
    private final static int VK_PRIOR = 0x21;
    private final static int VK_NEXT = 0x22;
    private final static int VK_END = 0x23;
    private final static int VK_HOME = 0x24;
    private final static int VK_LEFT = 0x25;
    private final static int VK_UP = 0x26;
    private final static int VK_RIGHT = 0x27;
    private final static int VK_DOWN = 0x28;
    public final static int VK_SELECT = 0x29;
    public final static int VK_PRINT = 0x2A;
    public final static int VK_EXECUTE = 0x2B;
    private final static int VK_SNAPSHOT = 0x2C;
    private final static int VK_INSERT = 0x2D;
    private final static int VK_DELETE = 0x2E;
    public final static int VK_HELP = 0x2F;
    /*
     * VK_0 - VK_9 are the same as ASCII '0' - '9' (0x30 - 0x39)
     * 0x40 : unassigned
     * VK_A - VK_Z are the same as ASCII 'A' - 'Z' (0x41 - 0x5A)
     */
    private final static int VK_0 = 0x30;
    private final static int VK_1 = 0x31;
    private final static int VK_2 = 0x32;
    private final static int VK_3 = 0x33;
    private final static int VK_4 = 0x34;
    private final static int VK_5 = 0x35;
    private final static int VK_6 = 0x36;
    private final static int VK_7 = 0x37;
    private final static int VK_8 = 0x38;
    private final static int VK_9 = 0x39;

    private final static int VK_A = 0x41;
    private final static int VK_B = 0x42;
    private final static int VK_C = 0x43;
    private final static int VK_D = 0x44;
    private final static int VK_E = 0x45;
    private final static int VK_F = 0x46;
    private final static int VK_G = 0x47;
    private final static int VK_H = 0x48;
    private final static int VK_I = 0x49;
    private final static int VK_J = 0x4A;
    private final static int VK_K = 0x4B;
    private final static int VK_L = 0x4C;
    private final static int VK_M = 0x4D;
    private final static int VK_N = 0x4E;
    private final static int VK_O = 0x4F;
    private final static int VK_P = 0x50;
    private final static int VK_Q = 0x51;
    private final static int VK_R = 0x52;
    private final static int VK_S = 0x53;
    private final static int VK_T = 0x54;
    private final static int VK_U = 0x55;
    private final static int VK_V = 0x56;
    private final static int VK_W = 0x57;
    private final static int VK_X = 0x58;
    private final static int VK_Y = 0x59;
    private final static int VK_Z = 0x5A;

    private final static int VK_LWIN = 0x5B;
    private final static int VK_RWIN = 0x5C;
    private final static int VK_APPS = 0x5D;
/*
 * 0x5E : reserved;
 */

    private final static int VK_SLEEP = 0x5F;

    private final static int VK_NUMPAD0 = 0x60;
    private final static int VK_NUMPAD1 = 0x61;
    private final static int VK_NUMPAD2 = 0x62;
    private final static int VK_NUMPAD3 = 0x63;
    private final static int VK_NUMPAD4 = 0x64;
    private final static int VK_NUMPAD5 = 0x65;
    private final static int VK_NUMPAD6 = 0x66;
    private final static int VK_NUMPAD7 = 0x67;
    private final static int VK_NUMPAD8 = 0x68;
    private final static int VK_NUMPAD9 = 0x69;
    private final static int VK_MULTIPLY = 0x6A;
    private final static int VK_ADD = 0x6B;
    private final static int VK_SEPARATOR = 0x6C;
    private final static int VK_SUBTRACT = 0x6D;
    private final static int VK_DECIMAL = 0x6E;
    private final static int VK_DIVIDE = 0x6F;
    private final static int VK_F1 = 0x70;
    private final static int VK_F2 = 0x71;
    private final static int VK_F3 = 0x72;
    private final static int VK_F4 = 0x73;
    private final static int VK_F5 = 0x74;
    private final static int VK_F6 = 0x75;
    private final static int VK_F7 = 0x76;
    private final static int VK_F8 = 0x77;
    private final static int VK_F9 = 0x78;
    private final static int VK_F10 = 0x79;
    private final static int VK_F11 = 0x7A;
    private final static int VK_F12 = 0x7B;
    private final static int VK_F13 = 0x7C;
    private final static int VK_F14 = 0x7D;
    private final static int VK_F15 = 0x7E;
    public final static int VK_F16 = 0x7F;
    public final static int VK_F17 = 0x80;
    public final static int VK_F18 = 0x81;
    public final static int VK_F19 = 0x82;
    public final static int VK_F20 = 0x83;
    public final static int VK_F21 = 0x84;
    public final static int VK_F22 = 0x85;
    public final static int VK_F23 = 0x86;
    public final static int VK_F24 = 0x87;

/*
 * 0x88 - 0x8F : unassigned;
 */

    private final static int VK_NUMLOCK = 0x90;
    private final static int VK_SCROLL = 0x91;

    /*
     * NEC PC-9800 kbd definitions
     */
    private final static int VK_OEM_NEC_EQUAL = 0x92;   // '=' key on numpad

//    /*
//     * Fujitsu/OASYS kbd definitions
//     */
    public final static int VK_OEM_FJ_JISHO = 0x92;   // 'Dictionary' key
    public final static int VK_OEM_FJ_MASSHOU = 0x93;   // 'Unregister word' key
    public final static int VK_OEM_FJ_TOUROKU = 0x94;   // 'Register word' key
    public final static int VK_OEM_FJ_LOYA = 0x95;   // 'Left OYAYUBI' key
    public final static int VK_OEM_FJ_ROYA = 0x96;   // 'Right OYAYUBI' key

/*
 * 0x97 - 0x9F : unassigned
 */

    /*
     * VK_L* & VK_R* - left and right Alt, Ctrl and Shift virtual keys.
     * Used only as parameters to GetAsyncKeyState() and GetKeyState().
     * No other API or message will distinguish left and right keys in this way.
     */
    private final static int VK_LSHIFT = 0xA0;
    private final static int VK_RSHIFT = 0xA1;
    private final static int VK_LCONTROL = 0xA2;
    private final static int VK_RCONTROL = 0xA3;
    private final static int VK_LMENU = 0xA4;
    private final static int VK_RMENU = 0xA5;

    public final static int VK_BROWSER_BACK = 0xA6;
    public final static int VK_BROWSER_FORWARD = 0xA7;
    public final static int VK_BROWSER_REFRESH = 0xA8;
    public final static int VK_BROWSER_STOP = 0xA9;
    public final static int VK_BROWSER_SEARCH = 0xAA;
    public final static int VK_BROWSER_FAVORITES = 0xAB;
    public final static int VK_BROWSER_HOME = 0xAC;

    public final static int VK_VOLUME_MUTE = 0xAD;
    public final static int VK_VOLUME_DOWN = 0xAE;
    public final static int VK_VOLUME_UP = 0xAF;
    public final static int VK_MEDIA_NEXT_TRACK = 0xB0;
    public final static int VK_MEDIA_PREV_TRACK = 0xB1;
    public final static int VK_MEDIA_STOP = 0xB2;
    public final static int VK_MEDIA_PLAY_PAUSE = 0xB3;
    public final static int VK_LAUNCH_MAIL = 0xB4;
    public final static int VK_LAUNCH_MEDIA_SELECT = 0xB5;
    public final static int VK_LAUNCH_APP1 = 0xB6;
    public final static int VK_LAUNCH_APP2 = 0xB7;

/*
 * 0xB8 - 0xB9 : reserved
 */

    public final static int VK_OEM_1 = 0xBA;   // ';:' for US
    public final static int VK_OEM_PLUS = 0xBB;   // '+' any country
    private final static int VK_OEM_COMMA = 0xBC;   // ',' any country
    public final static int VK_OEM_MINUS = 0xBD;   // '-' any country
    private final static int VK_OEM_PERIOD = 0xBE;   // '.' any country
    public final static int VK_OEM_2 = 0xBF;   // '/?' for US
    private final static int VK_OEM_3 = 0xC0;   // '`~' for US

/*
 * 0xC1 - 0xD7 : reserved
 */

/*
 * 0xD8 - 0xDA : unassigned
 */

    private final static int VK_OEM_4 = 0xDB;  //  '[{' for US
    public final static int VK_OEM_5 = 0xDC;  //  '\|' for US
    private final static int VK_OEM_6 = 0xDD;  //  ']}' for US
    public final static int VK_OEM_7 = 0xDE;  //  ''"' for US
    public final static int VK_OEM_8 = 0xDF;

/*
 * 0xE0 : reserved
 */

    /*
     * Various extended or enhanced keyboards
     */
    private final static int VK_OEM_AX = 0xE1;  //  'AX' key on Japanese AX kbd
    public final static int VK_OEM_102 = 0xE2;  //  "<>" or "\|" on RT 102-key kbd.
    public final static int VK_ICO_HELP = 0xE3;  //  Help key on ICO
    public final static int VK_ICO_00 = 0xE4;  //  00 key on ICO

    public final static int VK_PROCESSKEY = 0xE5;

    public final static int VK_ICO_CLEAR = 0xE6;


    public final static int VK_PACKET = 0xE7;

/*
 * 0xE8 : unassigned
 */


//    /*
//     * Nokia/Ericsson definitions
//     */
//    public final static int VK_OEM_RESET = 0xE9;

    public final static int VK_OEM_JUMP = 0xEA;
    public final static int VK_OEM_PA1 = 0xEB;
    public final static int VK_OEM_PA2 = 0xEC;
    public final static int VK_OEM_PA3 = 0xED;
    public final static int VK_OEM_WSCTRL = 0xEE;
    public final static int VK_OEM_CUSEL = 0xEF;
    public final static int VK_OEM_ATTN = 0xF0;
    public final static int VK_OEM_FINISH = 0xF1;
    public final static int VK_OEM_COPY = 0xF2;
    public final static int VK_OEM_AUTO = 0xF3;
    public final static int VK_OEM_ENLW = 0xF4;
    public final static int VK_OEM_BACKTAB = 0xF5;

    public final static int VK_ATTN = 0xF6;
    public final static int VK_CRSEL = 0xF7;
    public final static int VK_EXSEL = 0xF8;
    public final static int VK_EREOF = 0xF9;
    public final static int VK_PLAY = 0xFA;
    public final static int VK_ZOOM = 0xFB;
    public final static int VK_NONAME = 0xFC;
    public final static int VK_PA1 = 0xFD;
    public final static int VK_OEM_CLEAR = 0xFE;

    public static Key mapVKey(int vkey) {
        switch (vkey) {
            case VK_ESCAPE:
                return Key.ESCAPE;
            case VK_1:
                return Key._1;
            case VK_2:
                return Key._2;
            case VK_3:
                return Key._3;
            case VK_4:
                return Key._4;
            case VK_5:
                return Key._5;
            case VK_6:
                return Key._6;
            case VK_7:
                return Key._7;
            case VK_8:
                return Key._8;
            case VK_9:
                return Key._9;
            case VK_0:
                return Key._0;
            case VK_OEM_NEC_EQUAL:
                return Key.NUMPADEQUAL;
            case VK_BACK:
                return Key.BACK;
            case VK_TAB:
                return Key.TAB;
            case VK_Q:
                return Key.Q;
            case VK_W:
                return Key.W;
            case VK_E:
                return Key.E;
            case VK_R:
                return Key.R;
            case VK_T:
                return Key.T;
            case VK_Y:
                return Key.Y;
            case VK_U:
                return Key.U;
            case VK_I:
                return Key.I;
            case VK_O:
                return Key.O;
            case VK_P:
                return Key.P;
            case VK_OEM_4:
                return Key.LBRACKET;
            case VK_OEM_6:
                return Key.RBRACKET;
            case VK_RETURN:
                return Key.RETURN;
            case VK_CONTROL:
            case VK_LCONTROL:
                return Key.LCONTROL;
            case VK_A:
                return Key.A;
            case VK_S:
                return Key.S;
            case VK_D:
                return Key.D;
            case VK_F:
                return Key.F;
            case VK_G:
                return Key.G;
            case VK_H:
                return Key.H;
            case VK_J:
                return Key.J;
            case VK_K:
                return Key.K;
            case VK_L:
                return Key.L;
            case VK_OEM_3:
                return Key.GRAVE;
            case VK_SHIFT:
            case VK_LSHIFT:
                return Key.LSHIFT;
            case VK_Z:
                return Key.Z;
            case VK_X:
                return Key.X;
            case VK_C:
                return Key.C;
            case VK_V:
                return Key.V;
            case VK_B:
                return Key.B;
            case VK_N:
                return Key.N;
            case VK_M:
                return Key.M;
            case VK_OEM_COMMA:
                return Key.COMMA;
            case VK_OEM_PERIOD:
                return Key.PERIOD;
            case VK_RSHIFT:
                return Key.RSHIFT;
            case VK_MULTIPLY:
                return Key.MULTIPLY;
            case VK_MENU:
            case VK_LMENU:
                return Key.LALT;
            case VK_SPACE:
                return Key.SPACE;
            case VK_CAPITAL:
                return Key.CAPITAL;
            case VK_F1:
                return Key.F1;
            case VK_F2:
                return Key.F2;
            case VK_F3:
                return Key.F3;
            case VK_F4:
                return Key.F4;
            case VK_F5:
                return Key.F5;
            case VK_F6:
                return Key.F6;
            case VK_F7:
                return Key.F7;
            case VK_F8:
                return Key.F8;
            case VK_F9:
                return Key.F9;
            case VK_F10:
                return Key.F10;
            case VK_NUMLOCK:
                return Key.NUMLOCK;
            case VK_SCROLL:
                return Key.SCROLL;
            case VK_NUMPAD7:
                return Key.NUMPAD7;
            case VK_NUMPAD8:
                return Key.NUMPAD8;
            case VK_NUMPAD9:
                return Key.NUMPAD9;
            case VK_SUBTRACT:
                return Key.SUBTRACT;
            case VK_NUMPAD4:
                return Key.NUMPAD4;
            case VK_NUMPAD5:
                return Key.NUMPAD5;
            case VK_NUMPAD6:
                return Key.NUMPAD6;
            case VK_ADD:
                return Key.ADD;
            case VK_NUMPAD1:
                return Key.NUMPAD1;
            case VK_NUMPAD2:
                return Key.NUMPAD2;
            case VK_NUMPAD3:
                return Key.NUMPAD3;
            case VK_NUMPAD0:
                return Key.NUMPAD0;
            case VK_DECIMAL:
                return Key.DECIMAL;
            case VK_F11:
                return Key.F11;
            case VK_F12:
                return Key.F12;
            case VK_F13:
                return Key.F13;
            case VK_F14:
                return Key.F14;
            case VK_F15:
                return Key.F15;
            case VK_KANA:
                return Key.KANA;
            case VK_CONVERT:
                return Key.CONVERT;
            case VK_KANJI:
                return Key.KANJI;
            case VK_OEM_AX:
                return Key.AX;
            case VK_RCONTROL:
                return Key.RCONTROL;
            case VK_SEPARATOR:
                return Key.NUMPADCOMMA;
            case VK_DIVIDE:
                return Key.DIVIDE;
            case VK_SNAPSHOT:
                return Key.SYSRQ;
            case VK_RMENU:
                return Key.RALT;
            case VK_PAUSE:
                return Key.PAUSE;
            case VK_HOME:
                return Key.HOME;
            case VK_UP:
                return Key.UP;
            case VK_PRIOR:
                return Key.PAGEUP;
            case VK_LEFT:
                return Key.LEFT;
            case VK_RIGHT:
                return Key.RIGHT;
            case VK_END:
                return Key.END;
            case VK_DOWN:
                return Key.DOWN;
            case VK_NEXT:
                return Key.PAGEDOWN;
            case VK_INSERT:
                return Key.INSERT;
            case VK_DELETE:
                return Key.DELETE;
            case VK_LWIN:
                return Key.LWIN;
            case VK_RWIN:
                return Key.RWIN;
            case VK_APPS:
                return Key.APPS;
            case VK_SLEEP:
                return Key.SLEEP;
            default:
                return Key.UNKNOWN;
        }
    }
}
