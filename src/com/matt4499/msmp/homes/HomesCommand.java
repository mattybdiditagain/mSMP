package com.matt4499.msmp.homes;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
public class HomesCommand implements CommandExecutor {
    public HomeHelper homeHelper = Main.homeHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(homeHelper.getPlayerHomeCount(p) == 0) {
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ #dc143cYou have no homes!"));
            return true;
        }
        HashMap<String, Location> homes = homeHelper.getPlayerHomes(p);
        for(String homeName : homes.keySet()) {
            Location loc = homes.get(homeName);
            p.sendMessage(Main.hex("#dc143c&lHOMES &7➠ &7Home: #dc143c" + homeName + " &7- #dc143c" + Math.round(loc.getBlockX()) + "&7, #dc143c" + Math.round(loc.getBlockY()) + "&7, #dc143c" + Math.round(loc.getBlockZ()) + " &7- #dc143c" + loc.getWorld().getName()));
        }
        return true;
    }
}
