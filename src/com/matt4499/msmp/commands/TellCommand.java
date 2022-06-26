package com.matt4499.msmp.commands;

import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellCommand implements CommandExecutor  {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player sender = (Player)commandSender;
        if(args.length == 0) {
            sender.sendMessage(Main.hex("#dc143cYou did not put a message to send!"));
            return true;
        }
        Player receiver = Bukkit.getPlayer(args[0]);
        if(receiver == null) {
            sender.sendMessage(Main.hex("#dc143cThat player could not be found"));
            return true;
        } else {
            if(sender == receiver) {
                sender.sendMessage(Main.hex("#dc143cYou cannot send a message to yourself!"));
                return true;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i < args.length; i++) {
                sb.append(Main.hex(args[i]));
                sb.append(" ");
            }
            String message = sb.toString();
            sender.sendMessage(String.format(Main.hex("&7[You->%s&7] &7%s"), receiver.getDisplayName(), message));
            receiver.sendMessage(String.format(Main.hex("&7[%s->You&7] &7%s"), sender.getDisplayName(), message));
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.hasPermission("staff.msgspy")) {
                    p.sendMessage(Main.hex(String.format("#dc143cMSGSPY &7[%s->%s&7] &7%s", sender.getDisplayName(), receiver.getDisplayName(), message)));
                }
            }
            Main.logToGameLogs(String.format("[Message] %s -> %s: %s", sender.getDisplayName(), receiver.getDisplayName(), message));
            Main.lastsent.put(sender, receiver);
        }
        return true;
    }
}
