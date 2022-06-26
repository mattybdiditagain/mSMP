package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class TPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if(!player.hasPermission("staff.tpc")) {
            player.sendMessage(Main.hex("#dc143c&lSTAFF &f➠ #dc143cYou do not have permission to use this command."));
        } else {
            if(args.length < 2) {
                player.sendMessage(Main.hex("#dc143c&lSTAFF &f➠ #dc143cUsage: /tpc <X> <Y> <Z>"));
                return true;
            }
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            Location loc = new Location(player.getWorld(), x, y, z);
            player.teleport(loc);
            player.sendMessage(Main.hex("#dc143c&lSTAFF &f➠ &aTeleported to &7"+loc.getBlockX()+" "+loc.getBlockY()+" "+loc.getBlockZ()));
            Main.logToGameLogs("[TPC] " + player.getDisplayName() + " teleported to " +loc.getBlockX()+" "+loc.getBlockY()+" "+loc.getBlockZ());
        }
        return true;
    }
}
