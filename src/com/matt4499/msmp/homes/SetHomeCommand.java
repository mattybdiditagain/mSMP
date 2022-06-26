package com.matt4499.msmp.homes;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class SetHomeCommand implements CommandExecutor {
    public HomeHelper homeHelper = Main.homeHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(args.length == 0) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &f➠ #dc143cUsage: /sethome <name>"));
            return true;
        }
        String homeName = args[0];
        if(homeHelper.getPlayerHomeCount(p) == 3) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &f➠ #dc143cYou can only have 3 homes!"));
            return true;
        }
        if(homeHelper.getPlayerHomeLocation(p, homeName) != null) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &f➠ #dc143cHome " + homeName + " already exists!"));
            return true;
        }
        Location loc = p.getLocation();
        homeHelper.setPlayerHome(p, homeName, loc);
        p.sendMessage(Main.hex("#dc143c&lHOMES &f➠ &7Home #dc143c" + homeName + " &7set!"));
        return true;
    }
}
