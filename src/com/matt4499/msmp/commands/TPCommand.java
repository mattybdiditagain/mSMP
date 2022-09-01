package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class TPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if(!player.hasPermission("staff.tp")) {
            player.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cYou do not have permission to use this command."));
            return true;
        }
        if(args.length < 1) {
            player.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cUsage: /tp <player>"));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143cPlayer not found."));
            return true;
        }
        player.teleport(target);
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("staff.tp")) {
                p.sendMessage(Main.hex("#dc143c&lSTAFF &7➠ #dc143c" + player.getDisplayName() + " &7has teleported to #dc143c"+target.getDisplayName()));
            }
        }
        Main.logToGameLogs("[TP] " + player.getDisplayName() + " teleported to " +target.getDisplayName());
        return true;
    }
}
