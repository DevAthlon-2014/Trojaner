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

package de.static_interface.devathlon2014;

import com.comphenix.protocol.ProtocolLibrary;
import de.static_interface.devathlon2014.debug.Debug;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class DevathlonPlugin extends JavaPlugin {

    public static final boolean DEBUG = true;

    private static DevathlonPlugin instance;

    public void onEnable() {
        if(!validateDependencies()) {
            return;
        }

        instance = this;

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        Debug.log("registerListeners()");
        //ProtocolLibrary.getProtocolManager().addPacketListener();
    }

    private void registerCommands() {
        Debug.log("registerCommands()");

    }

    private boolean validateDependencies() {
        Plugin tmp = Bukkit.getPluginManager().getPlugin("ProtocolLib");
        if(tmp == null || !(tmp instanceof ProtocolLibrary)) {
            Debug.log("ProtocolLib check failed: " + (tmp == null ? " Plugin not found"
                                                                  : tmp.getName() + " is not an instance of ProtocolLibrary"));
            getLogger().info("Missing ProtocolLib! This plugin won't work without it.");
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }

        return true;
    }

    /**
     * @return Instance of this plugin
     * @throws IllegalStateException If the plugin is not initialized
     */
    public static DevathlonPlugin getInstance() {
        if(instance == null) {
            throw new IllegalStateException("Plugin is not initialized!");
        }
        return instance;
    }
}
