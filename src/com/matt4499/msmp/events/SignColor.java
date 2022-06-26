package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
public class SignColor implements Listener {
    @EventHandler
    public void signColor(SignChangeEvent e) {
        String[] lines = e.getLines();
        for(int i = 0; i < lines.length; i++) {
            e.setLine(i, Main.hex(lines[i]));
        }
    }
}
