package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.events.CrateHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class ConsoleVoteCrate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length < 1) {
            commandSender.sendMessage("Null vote received.");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            commandSender.sendMessage("Player not found");
            return true;
        }
        Bukkit.broadcastMessage(Main.hex("#dc143c&lVOTES &7➠ #dc143c" + target.getDisplayName() + " &7got a vote crate from #dc143c/vote"));
        Main.logToChatChannel("**VOTES:** " + target.getDisplayName() + " used /vote and got a vote crate!");
        Main.logToGameLogs("[VOTE] " + target.getDisplayName() + " used /vote and got a vote crate!");
        CrateHandler.givePlayerVoteCrate(target);
        return true;
    }
}
