package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.commands.BackCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
public class DeathCoordinates implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        BackCommand.back.put(e.getEntity(), e.getEntity().getLocation());
        e.getEntity().sendMessage(Main.hex("#dc143c&lSERVER &7➠ &7You died at #dc143c" + e.getEntity().getLocation().getBlockX() + "&7, #dc143c" + e.getEntity().getLocation().getBlockY() + "&7, #dc143c" + e.getEntity().getLocation().getBlockZ() + "&7 in #dc143c" + e.getEntity().getLocation().getWorld().getName()));
    }
}

