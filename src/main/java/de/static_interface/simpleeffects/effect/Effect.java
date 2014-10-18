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

import com.comphenix.packetwrapper.AbstractPacket;
import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import de.static_interface.simpleeffects.SimpleEffects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class Effect {

    private final String name;

    public Effect(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getUsage();

    public abstract boolean startEffect(Player player, String[] args, SimpleEffects plugin);

    private final Vector zeroVector = new Vector(0,0,0);
    protected WrapperPlayServerWorldParticles generateEffectPacket(WrapperPlayServerWorldParticles.ParticleEffect effect, Location loc)
    {
        WrapperPlayServerWorldParticles packet = new WrapperPlayServerWorldParticles();
        packet.setLocation(loc);
        packet.setOffset(zeroVector);
        packet.setNumberOfParticles(1);
        return packet;
    }

    private void sendPacket(Player player, AbstractPacket packet)
    {
        packet.sendPacket(player);
    }

    protected void sendPacket(AbstractPacket packet) {
        for(Player player: Bukkit.getOnlinePlayers()) {
            sendPacket(player, packet);
        }
    }
}
