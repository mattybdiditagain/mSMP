package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
public class TNTLogger implements Listener {
    @EventHandler
    public void onTNTPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() == Material.TNT) {
            double x = Math.round(e.getBlock().getLocation().getX());
            double y = Math.round(e.getBlock().getLocation().getBlockY());
            double z = Math.round(e.getBlock().getLocation().getBlockZ());
            String world = e.getBlock().getWorld().getName();
            Main.logToGameLogs("[TNT] " + e.getPlayer().getDisplayName() + " placed TNT at " + x + " " + y + " " + z + " in " + world + ".");
        }
    }
}
