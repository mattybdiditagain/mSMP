package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.delwarp")) {
            admin.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cYou do not have permission to use this command!"));
            return true;
        }
        if (strings.length == 0) {
            admin.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cUsage: /delwarp <name>"));
            return true;
        }
        String warpName = strings[0].toLowerCase();
        if(!Main.warpHelper.doesWarpExist(warpName)) {
            admin.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cWarp " + warpName + " does not exist!"));
            return true;
        }
        Main.warpHelper.removeWarp(warpName);
        admin.sendMessage(Main.hex("#dc143c&lWARPS &f➠ &7Warp " + warpName + " deleted!"));
        return true;
    }
}
