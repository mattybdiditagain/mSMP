package com.matt4499.msmp.commands;

import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class FCommand implements CommandExecutor {
    public HashMap<Player, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(isPlayerCD(p)) {
            p.sendMessage(Main.hex("#dc143cYou can only /F every 60 seconds"));
            return true;
        }
        int totalfs = (int) Main.getGlobalVar("totalfs");
        totalfs += 1;
        Main.setGlobalVar("totalfs", totalfs);
        Bukkit.broadcastMessage(Main.hex("#dc143c"+p.getDisplayName()+" &7has payed respects using #dc143c/F #dc143c("+totalfs +"#dc143c)"));
        Main.logToChatChannel(p.getDisplayName()+" has payed respects using /F ("+totalfs +")");
        return true;
    }

    public Boolean isPlayerCD(Player p) {
        int cooldownTime = 60;
        long curTime = System.currentTimeMillis();
        if(cooldown.get(p) == null) {
            cooldown.put(p, curTime);
            return false;
        } else {
            long lastUse = cooldown.get(p);
            long secondsLeft = (lastUse/1000 + cooldownTime) - (System.currentTimeMillis()/1000);
            if(secondsLeft > 0) {
                return true;
            } else {
                cooldown.put(p, System.currentTimeMillis());
                return false;
            }
        }
    }
}
