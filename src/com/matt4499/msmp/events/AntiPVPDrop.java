package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AntiPVPDrop implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                e.setKeepInventory(true); e.setDroppedExp(0); e.getDrops().clear();
                Main.logToGameLogs("[PVP] " + e.getEntity().getDisplayName() + " was killed by " + e.getEntity().getKiller().getDisplayName() + ", drops were cleared.");
            }
        }
    }

}
