package com.matt4499.msmp.types;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.matt4499.msmp.Main.*;
public class DataHelper implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        int joins = (int) getData(p.getUniqueId(), "totaljoins");
        joins += 1;
        setData(p.getUniqueId(), "totaljoins", joins);
        if(!p.hasPlayedBefore() || !ecoHelper.doesAccountExist(p.getUniqueId())) {
            ecoHelper.doesAccountExist(p.getUniqueId());
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        setData(p.getUniqueId(), "lastseen", System.currentTimeMillis());
    }

    @EventHandler(ignoreCancelled = true)
    public void blockBreak(BlockBreakEvent event) {
        int blocksBroke = (int) getGlobalVar("totalblocksbroke");
        blocksBroke += 1;
        setGlobalVar("totalblocksbroke", blocksBroke);
    }
    @EventHandler(ignoreCancelled = true)
    public void blockPlace(BlockPlaceEvent event) {
        int blocksPlaced = (int) getGlobalVar("totalblocksplaced");
        blocksPlaced += 1;
        setGlobalVar("totalblocksplaced", blocksPlaced);
    }
}
