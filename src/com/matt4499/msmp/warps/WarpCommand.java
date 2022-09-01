package com.matt4499.msmp.warps;
import com.matt4499.msmp.Main;
import com.matt4499.msmp.commands.BackCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class WarpCommand implements CommandExecutor, Listener {
    public WarpHelper warpHelper = Main.warpHelper;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p = (Player) commandSender;
        if(args.length == 0) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &7➠ #dc143cUsage: /warp <name>"));
            return true;
        }
        String warpName = args[0].toLowerCase();
        if(!warpHelper.doesWarpExist(warpName)) {
            p.sendMessage(Main.hex("#dc143c&lWARPS &7➠ &7Warp #dc143c" + warpName + " &7not found!"));
            return true;
        }
        Location loc = warpHelper.getWarpLocation(warpName);
        BackCommand.back.put(p, p.getLocation());
        p.teleport(loc);
        p.sendMessage(Main.hex("#dc143c&lWARPS &7➠ &7You teleported to warp: #dc143c" + warpName));
        Main.logToGameLogs("[TP] "+ p.getDisplayName() + " teleported to warp: " + warpName);
        return true;
    }

    // EXPERIMENTAL IN DEVELOPMENT

    @EventHandler
    public void warpClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains("Warps")) {
            e.setCancelled(true);
            Material clickedItem = e.getCurrentItem().getType();
            if (clickedItem == Material.PLAYER_HEAD) {
                SkullMeta skullMeta = (SkullMeta) e.getCurrentItem().getItemMeta();
                String warpName = skullMeta.getDisplayName();
                Player p = (Player) e.getWhoClicked();
                Location warpLoc = warpHelper.getWarpLocation(warpName);
                p.teleport(warpLoc);
                p.sendMessage(Main.hex("#dc143c&lWARPS &7➠ &7You teleported to warp: #dc143c" + warpName));
                Main.logToGameLogs("[TP] " + p.getDisplayName() + " teleported to warp: " + warpName);
            }
        }
    }
    public ItemStack warpButton(String warpName) {
        String owner = warpHelper.getWarpOwner(warpName);
        String desc = warpHelper.getWarpDescription(warpName);
        ItemStack warpButton = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta warpButtonMeta = (SkullMeta) warpButton.getItemMeta();
        assert warpButtonMeta != null;
        warpButtonMeta.setDisplayName(Main.hex("&7" + warpName));
        List<String> lore = new ArrayList<>();
        lore.add(Main.hex("&7Owner: " + owner + "\n&7Description: " + desc));
        warpButtonMeta.setLore(lore);
        warpButtonMeta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        warpButton.setItemMeta(warpButtonMeta);
        return warpButton;
    }
    public ItemStack pageButton(Boolean forward) {
        ItemStack pageButton = new ItemStack(Material.PAPER);
        ItemMeta pageButtonMeta = pageButton.getItemMeta();
        assert pageButtonMeta != null;
        if(forward) {
            pageButtonMeta.setDisplayName(Main.hex("&7Next Page"));
        } else {
            pageButtonMeta.setDisplayName(Main.hex("&7Previous Page"));
        }
        pageButton.setItemMeta(pageButtonMeta);
        return pageButton;
    }
}
