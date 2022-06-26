package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
public class BackCommand implements CommandExecutor {

    public static HashMap<Player, Location> back = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        Location loc = back.get(player);
        if(loc == null) {
            player.sendMessage(Main.hex("#dc143c&lBACK &f➠ #dc143cYou have no previous location."));
            return true;
        }
        back.put(player, player.getLocation());
        player.teleport(loc);
        player.sendMessage(Main.hex("#dc143c&lBACK &f➠ &aTeleported to your previous location."));
        return true;
    }
}
