package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.HashMap;
public class StaffModeCommand implements CommandExecutor, Listener {
    public HashMap<Player, Boolean> staffMode = new HashMap<>();
    public HashMap<Player, Location> staffLastLoc = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.staffmode")) { admin.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cYou do not have permission to use this command.")); return true; }
        if(!staffMode.containsKey(admin)) {
            staffMode.put(admin, false);
        }
        if(staffMode.get(admin)) {
            staffMode.put(admin, false);
            announceStaff("#dc143c&lSTAFF &7➠ #dc143c" + admin.getDisplayName() + " &7has #dc143cdisabled &7staff mode.");
            log("[Staff] " + admin.getDisplayName() + " has disabled staff mode.");
            admin.setGameMode(GameMode.SURVIVAL);
            admin.teleport(staffLastLoc.get(admin));
        } else {
            staffMode.put(admin, true);
            announceStaff("#dc143c&lSTAFF &7➠ #dc143c" + admin.getDisplayName() + " &7has &aenabled &7staff mode.");
            log("[Staff] " + admin.getDisplayName() + " has enabled staff mode.");
            staffLastLoc.put(admin, admin.getLocation());
            admin.setGameMode(GameMode.SPECTATOR);
        }
        return true;
    }
    public void announceStaff(String message) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.hasPermission("staff.staffmode")) {
                player.sendMessage(Main.hex(message));
            }
        }
    }
    public void log(String message) {
        Main.logToGameLogs(message);
    }
}
