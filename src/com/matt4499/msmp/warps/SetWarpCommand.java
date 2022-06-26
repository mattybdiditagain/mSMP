package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if (args.length == 0) {
            player.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cUsage: /setwarp <name> <description>"));
            return true;
        }
        String warpName = args[0].toLowerCase();
        if(Main.warpHelper.doesWarpExist(warpName)) {
            player.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cWarp " + warpName + " already exists!"));
            return true;
        }
        if(args.length < 2) {
            player.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cYou must specify a description for the warp!"));
            return true;
        }
        StringBuilder warpDescription = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            warpDescription.append(args[i]).append(" ");
        }
        Main.warpHelper.setWarp(player, warpName, player.getLocation(), warpDescription.toString());
        player.sendMessage(Main.hex("#dc143c&lWARPS &f➠ &7Warp #dc143c" + warpName + " &7set!"));
        return true;
    }
}
