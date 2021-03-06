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

import javax.annotation.Nullable;

public class Validate {

    /**
     * Validate that the given object is not null
     * @param o Object you want to validate
     * @throws IllegalArgumentException If the given object equals null
     */
    public static void notNull(@Nullable Object o) {
        if(o == null) {
            throw new IllegalArgumentException("The validated object is null");
        }
    }
}
