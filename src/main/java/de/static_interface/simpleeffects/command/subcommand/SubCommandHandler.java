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

package de.static_interface.simpleeffects.command.subcommand;

import de.static_interface.simpleeffects.effect.Effect;
import de.static_interface.simpleeffects.effect.EffectHandler;
import org.bukkit.command.CommandExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;

public class SubCommandHandler {
    public static HashMap<CommandExecutor, List<SubCommandExecutor>> subCommands = new HashMap<>();

    /**
     * Get all subcommands of a command
     * @param parentCommand Parent command
     * @return {@link Collection} instance of all subcommands
     */
    public static Collection<SubCommandExecutor> getSubCommands(@Nonnull CommandExecutor parentCommand) {
        List<SubCommandExecutor> registeredCommands = subCommands.get(parentCommand);
        if(registeredCommands == null) registeredCommands = new ArrayList<>();
        return registeredCommands;
    }

    /**
     * Register a subcommand
     * @param parentCommand Parent command
     * @param subCommand Subcommand to be registered
     */
    public static void registerSubCommand(@Nonnull CommandExecutor parentCommand, @Nonnull SubCommandExecutor subCommand) {
        Collection<Effect> effects = EffectHandler.getEffects();
        for(Effect effect: effects){
            if(effect.getName().equalsIgnoreCase(subCommand.getName())) {
                throw new IllegalArgumentException("SubCommand name \"" +  effect.getName()+ "\" is already registered as an effect!");
            }
        }

        List<SubCommandExecutor> registeredCommands = (List<SubCommandExecutor>) getSubCommands(parentCommand);

        for(SubCommandExecutor registeredCommand : registeredCommands) {
            if(registeredCommand == subCommand || registeredCommand.getInternalName().equals(subCommand.getInternalName())) {
                throw new IllegalStateException("SubCommand already registered");
            }
        }

        registeredCommands.add(subCommand);

        subCommands.put(parentCommand, registeredCommands);
    }
}
