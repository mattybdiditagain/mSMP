package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class WarpsCommand implements CommandExecutor {
    public WarpHelper warpHelper = Main.warpHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(warpHelper.getWarpCount() == 0) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ #dc143cThere are no warps!"));
        } else {
            StringBuilder warpList = new StringBuilder();
            warpHelper.getWarps().keySet().forEach(warpName -> {
                warpList.append(warpName).append("&7, #dc143c");
            });
            String warps = warpList.substring(0, warpList.length() - 9);
            p.sendMessage(Main.hex("#dc143c&lWARPS &f➠ &7Warps: #dc143c" + Main.hex(warps)));
        }
        return true;
    }
}
