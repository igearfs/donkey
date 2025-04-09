/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.donkey.util;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil {
    /**
     * Returns a resource as a stream by checking:
     * 
     * 1. The classpath for a resource with the specified name 2. For a file with the specified path
     * 
     * @param resourceName
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getResourceStream(Class<?> clazz, String resourceName) throws FileNotFoundException {
        // Always use forward slashes for classpath resources
        String cpResourceName = resourceName.startsWith("/") ? resourceName : "/" + resourceName;

        // Print the full path that is being used for loading the resource
        System.out.println("Attempting to load resource: " + cpResourceName);

        // Try loading the resource from the classpath
        InputStream is = clazz.getResourceAsStream(cpResourceName);

        // If not found in the classpath, try loading from the file system (as a fallback)
        if (is == null) {
            // Print the full file path for debugging when trying to load from the file system
            String filePath = new java.io.File(resourceName).getAbsolutePath();
            System.out.println("Resource not found in classpath. Attempting to load from file system: " + filePath);

            // Attempt to load from the file system
            is = new FileInputStream(resourceName);
        }

        return is;
    }




    public static void closeResourceQuietly(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }
}
