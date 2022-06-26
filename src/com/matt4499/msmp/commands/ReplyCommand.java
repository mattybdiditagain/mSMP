package com.matt4499.msmp.commands;

import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ReplyCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player sender = (Player)commandSender;
        if(!Main.lastsent.containsKey(sender)) {
            sender.sendMessage(Main.hex("#dc143cYou must /tell a message to someone first!"));
        } else {
            if(args.length == 0) {
                sender.sendMessage(Main.hex("#dc143cYou did not put a message to send!"));
                return true;
            }
            Player receiver = Main.lastsent.get(sender);
            StringBuilder sb = new StringBuilder();
            for (String arg : args) {
                sb.append(Main.hex(arg));
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
        }
        return true;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(Main.lastsent.containsValue(e.getPlayer())) {
            Main.lastsent.values().remove(e.getPlayer());
        }
    }
}
