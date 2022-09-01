package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        BackCommand.back.put(player, player.getLocation());
        Location spawn = new Location(Bukkit.getWorld("world"), -34.0, 66.0, 5.0);
        player.teleport(spawn);
        player.sendMessage(Main.hex("#dc143c&lSERVER &7➠ &7Teleported to spawn."));
        Main.logToGameLogs("[TP] " + player.getDisplayName() + " teleported to spawn.");
        return true;
    }
}
