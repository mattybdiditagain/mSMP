package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class RulesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage(Main.hex("#dc143c&lRULES &f➠ #dc143cFull rules can be found in /discord"));
        player.sendMessage(Main.hex("#dc143c&lRULES &f➠ #dc143c1. &7No griefing, stealing, hacking, or any other form of cheating/exploiting."));
        player.sendMessage(Main.hex("#dc143c&lRULES &f➠ &72. &7No spamming, excessive cursing, and no 18+ adult talk."));
        player.sendMessage(Main.hex("#dc143c&lRULES &f➠ #dc143c3. &7No advertising, or any form of advertising, including signs, books, chat, etc."));
        player.sendMessage(Main.hex("#dc143c&lRULES &f➠ &74. &7Just because something is not listed here, does not mean it is not allowed. Ask the staff if you have a question."));
        return true;
    }
}
