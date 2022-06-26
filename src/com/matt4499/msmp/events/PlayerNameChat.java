package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
public class PlayerNameChat implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onChat(AsyncPlayerChatEvent e) {

        String message = e.getMessage();
        for (String word : message.split(" ")) {
            if (Bukkit.getPlayerExact(word) != null) {
                Player target = Bukkit.getPlayerExact(word);
                assert target != null;
                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.5F, 0.5F);
                e.setMessage(message.replace(word, Main.hex("#dc143c"+word+"&f")));
            }
        }
    }
}
