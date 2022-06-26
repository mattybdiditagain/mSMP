package com.matt4499.msmp.commands;

import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class ReportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player reporter = (Player) commandSender;
        if(args.length == 0) {
            reporter.sendMessage(Main.hex("#dc143c&lSTAFF &f➠ #dc143cUsage: /report <player> <reason>"));
            return true;
        }
        String reported = args[0];
            if(reporter.getName().equalsIgnoreCase(reported)) {
                reporter.sendMessage(Main.hex("#dc143c&lREPORTS &f➠ #dc143cYou cannot report yourself!"));
                return true;
            }
            if(args.length < 2) {
                reporter.sendMessage(Main.hex("#dc143c&lREPORTS &f➠ #dc143cUsage: /report <player> <reason>"));
                return true;
            }
            StringBuilder sb = new StringBuilder();
            String reason;
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            reason = sb.toString();
            for(Player p : Bukkit.getOnlinePlayers()) {
                if(p.hasPermission("staff.reports")) {
                    p.sendMessage(Main.hex("#dc143c&lREPORTS &f➠ &7"+ reporter.getDisplayName() + " &7reported " + reported + " &7for: " + reason));
                }
            }
            Main.logToGameLogs("<@&805283750485295166> [Report] " + reporter.getDisplayName() + " reported " + reported + " for: " + reason);
            reporter.sendMessage(Main.hex("#dc143c&lREPORTS &f➠ &aStaff have received your report!"));
        return true;
    }
}
