package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.events.CrateHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class GiveCrateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (!player.hasPermission("staff.givecrates")) {
            player.sendMessage(Main.hex("#dc143c&lCRATES &f➠ #dc143cYou do not have permission to use this command."));
            return true;
        }
        if (strings.length == 0) {
            player.sendMessage("§cUsage: /givecrate <player>");
            return true;
        }
        Player target = Bukkit.getPlayer(strings[0]);
        if (target == null) {
            player.sendMessage(Main.hex("#dc143c&lCRATES &f➠ #dc143cPlayer not found"));
            return true;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "givevotepoint " + target.getName());
        CrateHandler.givePlayerVoteCrate(target);
        player.sendMessage(Main.hex("#dc143c&lCRATES &f➠ &7Crate given to #dc143c" + target.getName()));
        return true;
    }
}
