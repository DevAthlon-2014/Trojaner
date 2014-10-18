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

package de.static_interface.devathlon2014.effect;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EffectHandler {

    public static HashMap<String, Effect> effects = new HashMap<>();

    /**
     * Get an effect by its name
     * @param effectName Name of the effect
     * @return {@link Effect} instance of the effect or null if not registered
     */
    @Nullable
    public static Effect getEffectByName(@Nonnull String effectName) {
        return effects.get(effectName.toLowerCase());
    }

    /**
     * Register an effect
     * @param effect Effect to be registered
     */
    public static void registerEffect(@Nonnull Effect effect) {
        if(effect.getName().equalsIgnoreCase("help") || effect.getName().equalsIgnoreCase("listeffects")) {
            throw new IllegalArgumentException("Effect name \"" +  effect.getName()+ "\" is reserved!");
        }

        if(effects.get(effect.getName()) != null || effects.values().contains(effect)) {
            throw new IllegalStateException("Effect already registered");
        }

        effects.put(effect.getName().toLowerCase(), effect);
    }
}
