package com.matt4499.msmp.homes;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.commands.BackCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class HomeCommand implements CommandExecutor {
    public HomeHelper homeHelper = Main.homeHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if (args.length == 0) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ #dc143cUsage: /home <name>"));
            return true;
        }
        String homeName = args[0];
        Location loc = homeHelper.getPlayerHomeLocation(p, homeName);
        if (loc == null) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ #dc143cHome " + homeName + " not found!"));
            return true;
        }
        BackCommand.back.put(p, p.getLocation());
        p.teleport(loc);
        p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ &7You teleported to home: #dc143c" + homeName));
        Main.logToGameLogs("[TP] "+ p.getDisplayName() + " teleported to home: " + homeName);
        return true;
    }
}
