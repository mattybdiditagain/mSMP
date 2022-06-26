package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Locale;
public class AntiSpam implements Listener {
    public HashMap<Player, String> lastMessage = new HashMap<>();
    @EventHandler(ignoreCancelled = true)
    public void onSpeak(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if(e.isCancelled()) {
            return;
        }
        if (lastMessage.containsKey(player)) {
            if (lastMessage.get(player).equalsIgnoreCase(e.getMessage())) {
                e.setCancelled(true);
                player.sendMessage(Main.hex("#dc143c&lSERVER &f➠ #dc143cYou cannot send the same message again."));
                return;
            }
        }
        lastMessage.put(player, e.getMessage().toLowerCase(Locale.ENGLISH));
    }
}
