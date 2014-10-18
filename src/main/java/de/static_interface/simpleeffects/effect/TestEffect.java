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
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class TestEffect extends Effect {
    public TestEffect() {
        super("test");
    }

    @Override
    public String getUsage() {
        return "<radius> <range>";
    }

    @Override
    public boolean startEffect(Player player, String[] args, SimpleEffects plugin) {
        if(args.length < 2) {
            return false;
        }

        int radius;
        int range;

        try {
            radius = Integer.valueOf(args[0]);
            range = Integer.valueOf(args[1]);
        }
        catch(NumberFormatException ignored) {
            return false;
        }

        double playerX = player.getLocation().getX();
        double playerY = player.getLocation().getY();
        double playerZ = player.getLocation().getZ();

        for(int particlePosX = 0; particlePosX < range; particlePosX++) {
            for(int particlePosZ = 0; particlePosZ < radius; particlePosZ++) {
                World world = player.getWorld();

                Location loc = new Location(world, playerX + particlePosX, playerY, playerZ + particlePosZ);
            }
        }

        return true;
    }
}
