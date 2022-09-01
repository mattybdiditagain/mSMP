package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
public class GlobalStatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        String gBlocksBroken = DecimalFormat.getNumberInstance().format((int) Main.getGlobalVar("totalblocksbroke"));
        String gBlocksPlaced = DecimalFormat.getNumberInstance().format((int) Main.getGlobalVar("totalblocksplaced"));
        p.sendMessage(Main.hex("#dc143c========== mSMP GLOBAL STATS =========="));
        p.sendMessage(Main.hex("&6Blocks Broken: " + gBlocksBroken));
        p.sendMessage(Main.hex("&6Blocks Placed: " + gBlocksPlaced));
        return true;
    }
}
