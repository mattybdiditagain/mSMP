package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class WarpInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(!p.hasPermission("staff.warpinfo")) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cYou do not have permission to use this command!"));
            return true;
        }
        if (strings.length == 0) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cUsage: /warpinfo <name>"));
            return true;
        }
        String warpName = strings[0];
        if(!Main.warpHelper.doesWarpExist(warpName)) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cWarp " + warpName + " does not exist!"));
            return true;
        }
        String owner = Main.warpHelper.getWarpOwner(warpName);
        String location = Main.warpHelper.getWarpLocation(warpName).toString();
        String description = Main.warpHelper.getWarpDescription(warpName);
        p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ &7Warp #dc143c" + warpName + " &7owned by #dc143c" + owner + " &7at #dc143c" + location + " &7with description: #dc143c" + description));
        return true;
    }
}
