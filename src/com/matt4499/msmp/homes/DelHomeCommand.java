package com.matt4499.msmp.homes;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class DelHomeCommand implements CommandExecutor {
    public HomeHelper homeHelper = Main.homeHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(args.length == 0) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ #dc143cUsage: /delhome <name>"));
            return true;
        }
        if(homeHelper.getPlayerHomeCount(p) == 0) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ #dc143cYou have no homes!"));
            return true;
        }
        String homeName = args[0];
        if(homeHelper.getPlayerHomeLocation(p, homeName) == null) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ &7Home #dc143c" + homeName + " &7not found!"));
            return true;
        }
        homeHelper.removePlayerHome(p, homeName);
        p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ &7Home #dc143c" + homeName + " &7deleted!"));
        return true;
    }
}
