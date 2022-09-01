package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Locale;
public class ChatFilter implements Listener {
    public static HashMap<String, String> filtered = new HashMap<>();
    public static HashMap<String, String> phrases = new HashMap<>();
    public ChatFilter() {
        filtered.put("nigger", "ban %p [ChatFilter] Racism #1");
        filtered.put("niggers", "ban %p [ChatFilter] Racism #1");
        filtered.put("n!ggers", "bap %p [ChatFilter] Racism #1 Attempted Bypass");
        filtered.put("n!gger$", "bap %p [ChatFilter] Racism #1 Attempted Bypass");
        filtered.put("nigger$", "bap %p [ChatFilter] Racism #1 Attempted Bypass");
        filtered.put("nigg", "bap %p [ChatFilter] Racism #1 Attempted Bypass");
        filtered.put("nigs", "bap %p [ChatFilter] Racism #1 Attempted Bypass");
        filtered.put("spic", "ban %p [ChatFilter] Racism #2");
        filtered.put("kys", "tempmute %p 30m [ChatFilter] Phrases #1");
        filtered.put("ky$", "ban %p [ChatFilter] Phrases #1 Attempted Bypass");
        filtered.put("testtriggerword", "ban %p [ChatFilter] Test Filter #1");
        filtered.put("anal", "tempban %p 4h [ChatFilter] Inappropriate #1");
        filtered.put("cunt", "tempban %p 4h [ChatFilter] Inappropriate #2");
        filtered.put("gay", "warn %p [ChatFilter] Inappropriate #3");
        filtered.put("g@y", "tempmute %p 1h [ChatFilter] Inappropriate #3 Attempted Bypass");
        filtered.put("gae", "warn %p [ChatFilter] Inappropriate Message");
        filtered.put("cock", "warn %p [ChatFilter] Inappropriate Message");
        filtered.put("c0ck", "tempmute %p 15m [ChatFilter] Inappropriate Message Bypass");
        filtered.put("dick", "warn %p [ChatFilter] Inappropriate Message");
        filtered.put("d@ck", "tempmute %p 15m [ChatFilter] Inappropriate Message Bypass");
        filtered.put("fag", "warn %p [ChatFilter] Inappropriate Message");
        filtered.put("faggot", "warn %p [ChatFilter] Inappropriate Message");
        filtered.put("f@g", "ban %p [ChatFilter] Inappropriate #4");

        phrases.put("join my server", "tempban -s %p 1w [ChatFilter] Advertising");

    }
    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {
        for(String word : e.getMessage().split(" ")) {
            for(String filter : filtered.keySet()) {
                if(word.equalsIgnoreCase(filter.split(" ")[0])) {
                    e.setCancelled(true);
                    Player p = e.getPlayer();
                    Main.logToGameLogs(String.format("[ChatFilter:Words] **%s** tried to say: **%s**", p.getName(), e.getMessage()));
                    String command = filtered.get(filter).replace("%p", p.getName());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command), 1L);
                    return;
                }
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onChat2(AsyncPlayerChatEvent e) {
        String message = e.getMessage().toLowerCase(Locale.ENGLISH);
        for(String phrase : phrases.keySet()) {
            if(message.contains(phrase)) {
                e.setCancelled(true);
                Player p = e.getPlayer();
                Main.logToGameLogs(String.format("[ChatFilter:Phrases] **%s** tried to say: **%s**", p.getName(), e.getMessage()));
                String command = phrases.get(phrase).replace("%p", p.getName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command), 1L);
                return;
            }
        }
    }
}
