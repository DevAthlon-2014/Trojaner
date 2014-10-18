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

package de.static_interface.simpleeffects.command.effects;

import de.static_interface.simpleeffects.SimpleEffects;
import de.static_interface.simpleeffects.command.subcommand.SubCommandExecutor;
import de.static_interface.simpleeffects.command.subcommand.SubCommandHandler;
import de.static_interface.simpleeffects.effect.Effect;
import de.static_interface.simpleeffects.effect.EffectHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;

public class EffectsCommand implements CommandExecutor {

    public static final String CMD_PREFIX = ChatColor.AQUA + "[SimpleEffects]" + ChatColor.RESET;

    private final SimpleEffects plugin;

    public EffectsCommand(SimpleEffects plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command :(");
            return true;
        }

        if(args.length < 1) {
            return false;
        }

        String alias = label.split(" ")[0];

        Player player = (Player)sender;
        String action = args[0];

        String[] actionArgs = new String[0];
        if(args.length > 1) {
            actionArgs  = Arrays.copyOfRange(args, 1, args.length);
        }


        if(handleCommand(player, action, actionArgs)) {
            return true;
        }


        Effect effect = EffectHandler.getEffectByName(action);

        if(effect == null) {
            sendMessage(player, ChatColor.RED + "Unbekannter Effect oder Command: " + action);
            sendMessage(player, ChatColor.GOLD + "Nutze /" + alias  + " help für eine Liste der verfügbaren Befehle");
            sendMessage(player, ChatColor.GOLD + "Nutze /" + alias + " listeffects für eine Liste der verfügbaren Effekte");
            return true;
        }

        if(!effect.startEffect(player, actionArgs, plugin)) {
            sendMessage(player, "Wrong usage! Usage: /" + alias + " " + action + " " + effect.getUsage());
        }
        return true;
    }

    private void sendMessage(Player player, String msg) {
        player.sendMessage(CMD_PREFIX + " " + msg);
    }

    /**
     * Handle an effect subcommand
     * @param cmd Command string
     * @param player Command executor
     * @return True if the given String was a command and has been executed
     */
    private boolean handleCommand(Player player, String cmd, String[] args) {
        Collection<SubCommandExecutor> subCommands = SubCommandHandler.getSubCommands(this);

        for(SubCommandExecutor executor : subCommands) {
            if(executor.getInternalName().equals(cmd)) {
                executor.onCommand(this, player, args);
                return true;
            }
        }
        return false;
    }
}
