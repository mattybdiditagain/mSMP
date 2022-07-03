package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import javax.annotation.Nonnull;
public class FarmWorldCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String s, String[] args) {
        Player p = (Player) sender;
        if (args.length < 1) {
            World farmworld = Bukkit.getWorld("farmworld");
            Location fwspawn = new Location(farmworld, 0, 101, 0);
            p.teleport(fwspawn);
            return true;
        } else {
            if (args[0].equalsIgnoreCase("create")) {
                if (!p.hasPermission("staff.fwc")) return true;
                WorldCreator wc = new WorldCreator("farmworld");
                wc.environment(World.Environment.NORMAL);
                wc.type(WorldType.NORMAL);
                World farmworld = wc.createWorld();
                assert farmworld != null;
                farmworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                farmworld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
                farmworld.setGameRule(GameRule.DO_FIRE_TICK, false);
                farmworld.setTime(6000);
                p.sendMessage(Main.hex("#dc143cSTAFF &f> &aSuccessfully created farmworld!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("createnether")) {
                if (!p.hasPermission("staff.fwc")) return true;
                WorldCreator wc = new WorldCreator("farmworld_nether");
                wc.environment(World.Environment.NETHER);
                wc.type(WorldType.NORMAL);
                wc.createWorld();
                p.sendMessage(Main.hex("#dc143cSTAFF &f> &aSuccessfully created farmworld_nether!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("nether")) {
                World netherfarmworld = Bukkit.getWorld("farmworld_nether");
                Location netherfwspawn = new Location(netherfarmworld, 0, 101, 0);
                p.teleport(netherfwspawn);
                return true;
            }
        }
        return true;
    }
}
