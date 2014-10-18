/*
 * Copyright (c) 2014 http://static-interface.de and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.static_interface.simpleeffects.debug;

import de.static_interface.simpleeffects.SimpleEffects;

import javax.annotation.Nullable;

/**
 * This class contains some debug features which won't affect the final release
 */
public class Debug {
    /**
     * @return The {@link Class} instance of the class calling the method calling this.
     */
    @Nullable
    public static Class<?> getCallerCallerClassName() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String callerClassName = null;
        for (int i = 1; i < stElements.length; i++) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(Debug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
                if (callerClassName == null) {
                    callerClassName = ste.getClassName();
                } else if (!callerClassName.equals(ste.getClassName())) {
                    try {
                        return Class.forName(ste.getClassName());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Logs the given message on debug releases
     * @param s Message to log
     */
    public static void log(String s) {
        if (SimpleEffects.DEBUG) {
            return;
        }
        log("[Debug] " + getCallerCallerClassName() + ".class: " + s);
    }

    /**
     * Logs the given message on debug releases
     * @param s Message you want to log
     * @param tr Throwable you want to log
     */
    public static void log(String s, Throwable tr) {
        if (SimpleEffects.DEBUG) {
            return;
        }
        log(s);
        tr.printStackTrace();
    }

    /**
     * Logs the given message on debug releases
     * @param tr Throwable you want to log
     */
    public static void log(Throwable tr) {
        log("Unexpected exception occurred: ", tr);
    }
}
