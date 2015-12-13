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


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Loads all plugins.
 *
 * @author Michael Martak
 * @version %I% %G%
 */
class PluginClassLoader extends ClassLoader {

    /**
     * File filter for JAR files
     */
    private static final FileFilter JAR_FILTER = new JarFileFilter();
    /**
     * Location of directory to look for plugins
     */
    private static String pluginDirectory;

// --Commented out by Inspection START (11/29/2015 12:48 AM):
//    /**
//     * Create a new class loader for loading plugins
//     */
//    public PluginClassLoader() {
//        super(Thread.currentThread().getContextClassLoader());
//    }
// --Commented out by Inspection STOP (11/29/2015 12:48 AM)

    /**
     * Overrides findClass to first look in the parent class loader,
     * then try loading the class from the plugin file system.
     */
    protected Class findClass(@NotNull String name)
            throws ClassNotFoundException {
        // Try loading the class from the file system.
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    /**
     * Load the class data from the file system
     */
    @NotNull
    private byte[] loadClassData(@NotNull String name)
            throws ClassNotFoundException {
        if (pluginDirectory == null) {
            pluginDirectory = DefaultControllerEnvironment.libPath +
                    File.separator + "controller";
        }
        try {
            return loadClassFromDirectory(name);
        } catch (Exception e) {
            try {
                return loadClassFromJAR(name);
            } catch (IOException e2) {
                throw new ClassNotFoundException(name, e2);
            }
        }
    }

    /**
     * Load the class data from the file system based on parsing
     * the class name (packages).
     */
    @NotNull
    private byte[] loadClassFromDirectory(@NotNull String name)
            throws ClassNotFoundException, IOException {
        // Parse the class name into package directories.
        // Look for the class in the plugin directory.
        StringTokenizer tokenizer = new StringTokenizer(name, ".");
        StringBuilder path = new StringBuilder(pluginDirectory);
        while (tokenizer.hasMoreTokens()) {
            path.append(File.separator);
            path.append(tokenizer.nextToken());
        }
        path.append(".class");
        File file = new File(path.toString());
        if (!file.exists()) {
            throw new ClassNotFoundException(name);
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        assert file.length() <= Integer.MAX_VALUE;
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        int length2 = fileInputStream.read(bytes);
        assert length == length2;
        return bytes;
    }

    /**
     * Scans through the plugin directory for JAR files and
     * attempts to load the class data from each JAR file.
     */
    @NotNull
    private byte[] loadClassFromJAR(String name)
            throws ClassNotFoundException, IOException {
        File dir = new File(pluginDirectory);
        File[] jarFiles = dir.listFiles(JAR_FILTER);
        if (jarFiles == null) {
            throw new ClassNotFoundException("Could not find class " + name);
        }
        for (File jarFile : jarFiles) {
            JarFile jarfile = new JarFile(jarFile);
            JarEntry jarentry = jarfile.getJarEntry(name + ".class");
            if (jarentry != null) {
                InputStream jarInputStream = jarfile.getInputStream(jarentry);
                assert jarentry.getSize() <= Integer.MAX_VALUE;
                int length = (int) jarentry.getSize();
                assert length >= 0;
                byte[] bytes = new byte[length];
                int length2 = jarInputStream.read(bytes);
                assert length == length2;
                return bytes;
            }
        }
        throw new FileNotFoundException(name);
    }


//    /**
//     * Filters out all non-JAR files, based on whether or not they
//     * end in ".JAR" (case-insensitive).
//     */

    private static class JarFileFilter implements FileFilter {
        public boolean accept(@NotNull File file) {
            return file.getName().toUpperCase().endsWith(".JAR");
        }
    }
}

