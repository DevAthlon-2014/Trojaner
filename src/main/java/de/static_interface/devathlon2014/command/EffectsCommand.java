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

package de.static_interface.devathlon2014.command;

import de.static_interface.devathlon2014.SimpleEffects;
import de.static_interface.devathlon2014.effect.Effect;
import de.static_interface.devathlon2014.effect.EffectHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

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

        Player player = (Player)sender;
        String action = args[0];

        if(handleCommand(player, action)) {
            return true;
        }

        String[] effectArgs = new String[0];
        if(args.length > 1) {
            effectArgs  = Arrays.copyOfRange(args, 1, args.length);
        }

        Effect effect = EffectHandler.getEffectByName(action);

        if(effect == null) {
            sendMessage(player, "Unbekannter Effect oder Command: " + action);
            sendMessage(player, "Nutze /effect help für eine Liste der verfügbaren Befehle");
            sendMessage(player, "Nutze /effect listeffects für eine Liste der verfügbaren Effekte");
            return true;
        }

        if(!effect.startEffect(player, effectArgs, plugin)) {
            sendMessage(player, effect.getUsage());
        }
        return true;
    }

    private void sendMessage(Player player, String msg) {
        player.sendMessage(CMD_PREFIX + msg);
    }

    /**
     * Handle an effect subcommand
     * @param cmd Command string
     * @param player Command executor
     * @return True if the given String was a command and has been executed
     */
    private boolean handleCommand(Player player, String cmd) {
        switch(cmd.toLowerCase()) {
            case "help":
                //Todo
                return true;

            case "listeffects":
                //Todo
                return true;
        }
        return false;
    }
}
