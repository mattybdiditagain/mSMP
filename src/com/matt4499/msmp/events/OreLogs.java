package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
public class OreLogs implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        switch (e.getBlock().getType()) {
            case DIAMOND_ORE -> Main.logToGameLogs("[OreLogs] " + e.getPlayer().getDisplayName() + " broke diamond ore at " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + " in " + e.getBlock().getWorld().getName());
            case EMERALD_ORE -> Main.logToGameLogs("[OreLogs] " + e.getPlayer().getDisplayName() + " broke emerald ore at " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + " in " + e.getBlock().getWorld().getName());
            case DEEPSLATE_DIAMOND_ORE -> Main.logToGameLogs("[OreLogs] " + e.getPlayer().getDisplayName() + " broke deepslate diamond ore at " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + " in " + e.getBlock().getWorld().getName());
            case DEEPSLATE_EMERALD_ORE -> Main.logToGameLogs("[OreLogs] " + e.getPlayer().getDisplayName() + " broke deepslate emerald ore at " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + " in " + e.getBlock().getWorld().getName());
            case ANCIENT_DEBRIS -> Main.logToGameLogs("[OreLogs] " + e.getPlayer().getDisplayName() + " broke ancient debris at " + e.getBlock().getX() + ", " + e.getBlock().getY() + ", " + e.getBlock().getZ() + " in " + e.getBlock().getWorld().getName());
            default -> {
            }
        }
    }
}
