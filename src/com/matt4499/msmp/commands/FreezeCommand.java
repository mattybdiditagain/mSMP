package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
public class FreezeCommand implements CommandExecutor, Listener {
    public HashMap<Player, Boolean> frozen = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.freeze")) {
            admin.sendMessage(Main.hex("#dc143c&lFREEZE &7➠ &cYou do not have permission to use this command!"));
            return true;
        }
        if(args.length == 0) {
            admin.sendMessage(Main.hex("#dc143c&lREPORTS &7➠ #dc143cUsage: /freeze <player>"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null) {
            admin.sendMessage(Main.hex("#dc143c&lREPORTS &7➠ #dc143cPlayer not found."));
            return true;
        }
        if(isPlayerFrozen(target)) {
            frozen.remove(target);
            target.sendMessage(Main.hex("#dc143c&lFREEZE &7➠ &7You have been unfrozen by #dc143c" + admin.getDisplayName()));
            announceStaff("#dc143c"+target.getDisplayName() + " &7has been unfrozen by #dc143c" + admin.getDisplayName());
            Main.logToGameLogs("[Freeze] " + admin.getDisplayName() + " unfroze " + target.getDisplayName());
        } else {
            frozen.put(target, true);
            target.sendMessage(Main.hex("#dc143c&lFREEZE &7➠ &cYou have been frozen by " + admin.getDisplayName()));
            announceStaff("#dc143c"+target.getDisplayName() + " &7has been frozen by #dc143c" + admin.getDisplayName());
            Main.logToGameLogs("[Freeze] " + admin.getDisplayName() + " froze " + target.getDisplayName());
        }
        return true;
    }
    public boolean isPlayerFrozen(Player p) {
        if(frozen.containsKey(p)) {
            return frozen.get(p);
        } else {
            frozen.put(p, false);
            return false;
        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if(isPlayerFrozen(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    public void announceStaff(String message) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("staff.freeze")) {
                p.sendMessage(Main.hex("#dc143c&lFREEZE &7➠ &7" + message));
            }
        }
    }
}
