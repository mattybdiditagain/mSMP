package com.matt4499.msmp.commands;

import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

import static com.matt4499.msmp.Main.hex;

public class AFKCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player)commandSender;
        if(isPlayerAFK(sender)) {
            Main.afkmap.put(sender.getUniqueId(), false);
            Main.announce("&7&o"+sender.getDisplayName()+" &7&ois no longer AFK");
            Main.logToChatChannel(sender.getDisplayName()+" is no longer AFK");
            Main.logToGameLogs(sender.getDisplayName()+" is no longer AFK");
        } else {
            Main.afkmap.put(sender.getUniqueId(), true);
            Main.announce("&7&o"+sender.getDisplayName()+" &7&ois now AFK");
            Main.logToChatChannel(sender.getDisplayName()+" is now AFK");
            Main.logToGameLogs(sender.getDisplayName()+" is now AFK");
        }
        return true;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Main.afkmap.put(event.getPlayer().getUniqueId(), false);
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player p = (Player)e.getEntity();
            if(isPlayerAFK(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Main.afkmap.remove(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) { checkAFK(event.getPlayer()); }
    @EventHandler
    public void onInvInt(InventoryOpenEvent event) { checkAFK((Player) event.getPlayer()); }
    @EventHandler
    public void onPlace(BlockPlaceEvent event) { checkAFK(event.getPlayer()); }
    @EventHandler
    public void onEntInt(PlayerInteractEntityEvent e) { checkAFK(e.getPlayer());}
    @EventHandler
    public void onBreak(BlockBreakEvent e) { checkAFK(e.getPlayer()); }

    public void checkAFK(Player p) {
        if(isPlayerAFK(p)) {
            Main.announce("&7&o" + (p.getDisplayName() + " &7&ois no longer AFK"));
            Main.logToChatChannel(p.getDisplayName()+" is no longer AFK");
            Main.logToGameLogs(p.getDisplayName()+" is no longer AFK");
            Main.afkmap.put(p.getUniqueId(), false);
        }
    }
    public static Boolean isPlayerAFK(Player p) {
        if(!Main.afkmap.containsKey(p.getUniqueId())) {
            Main.afkmap.put(p.getUniqueId(), false);
            return false;
        } else {
            return Main.afkmap.get(p.getUniqueId());
        }
    }

}
