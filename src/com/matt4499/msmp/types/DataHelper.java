package com.matt4499.msmp.types;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.matt4499.msmp.Main.getData;
import static com.matt4499.msmp.Main.setData;

public class DataHelper implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        int joins = (int) getData(p.getUniqueId(), "totaljoins");
        joins += 1;
        setData(p.getUniqueId(), "totaljoins", joins);
    }

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        setData(p.getUniqueId(), "lastseen", System.currentTimeMillis());
    }
}
