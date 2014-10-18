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

package de.static_interface.simpleeffects.effect;

import de.static_interface.simpleeffects.SimpleEffects;
import de.static_interface.simpleeffects.command.subcommand.SubCommandExecutor;
import de.static_interface.simpleeffects.command.subcommand.SubCommandHandler;

import java.util.Collection;
import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EffectHandler {
    private static HashMap<String, Effect> effects = new HashMap<>();

    /**
     * @return {@link Collection} instance of all effects
     */
    public static Collection<Effect> getEffects() {
        return effects.values();
    }

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
        for(SubCommandExecutor cmd : SubCommandHandler.getSubCommands(SimpleEffects.getInstance().getEffectsCommand())) {
            if(cmd.getInternalName().equalsIgnoreCase(effect.getName()))
                throw new IllegalArgumentException("Effect name \"" +  effect.getName()+ "\" is already registered as command!");
        }

        if(effects.get(effect.getName()) != null || effects.values().contains(effect)) {
            throw new IllegalStateException("Effect already registered");
        }

        effects.put(effect.getName().toLowerCase(), effect);
    }
}
