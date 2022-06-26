package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class LCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if(args.length < 1) {
            player.sendMessage(Main.hex("#dc143cUsage: /l <player>"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Main.hex("#dc143cPlayer not found."));
            return true;
        }
        int targetLs = (int) Main.getData(target.getUniqueId(), "totalls");
        targetLs += 1;
        Main.setData(target.getUniqueId(), "totalls", targetLs);
        Bukkit.broadcastMessage(Main.hex("#dc143c"+player.getDisplayName()+" &7has tossed the L to #dc143c"+target.getDisplayName()+" #dc143c("+targetLs+"#dc143c)"));
        Main.logToChatChannel(player.getDisplayName()+" has tossed the L to "+target.getDisplayName()+" ("+targetLs+")");
        return true;
    }
}
