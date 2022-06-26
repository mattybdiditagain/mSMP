package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nullable;
import java.util.List;
@SuppressWarnings("deprecation")
public class PunishCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player admin = (Player) commandSender;
        if(!admin.hasPermission("staff.punish")) {
            admin.sendMessage(Main.hex("#dc143c&lPUNISH &f➠ #dc143cYou do not have permission to use this command."));
            return true;
        }
        if(args.length == 0) {
            admin.sendMessage(Main.hex("#dc143c&lPUNISH &f➠ #dc143cUsage: /punish <player>"));
            return true;
        }
        String target = args[0];
        Inventory inv = Bukkit.createInventory(null, 54, Main.hex("#dc143c&lPUNISH" + " &f➠ #dc143c" + target));
        ItemStack filler = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        assert fillerMeta != null;
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);
        for(int i = 0; i < 54; i++) {
            inv.setItem(i, filler);
        }
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        assert skullMeta != null;
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(target));
        skullMeta.setDisplayName(Main.hex("&7Click to view " + target + "'s history"));
        skull.setItemMeta(skullMeta);
        inv.setItem(4, skull);


        inv.setItem(9, generateLabel("&7Warns >>>"));
        inv.setItem(18, generateLabel("&7Mutes >>>"));
        inv.setItem(27, generateLabel("&7Bans >>>"));

        List<String> warn1lore = new java.util.ArrayList<>();
        warn1lore.add(Main.hex("#dc143cWarning"));
        inv.setItem(10, generateBook("&7Spamming/Repeating Phrases", warn1lore));

        List<String> warn2lore = new java.util.ArrayList<>();
        warn2lore.add(Main.hex("#dc143cWarning"));
        inv.setItem(11, generateBook("&7Excessive Swearing", warn2lore));

        List<String> warn3lore = new java.util.ArrayList<>();
        warn3lore.add(Main.hex("#dc143cWarning"));
        inv.setItem(12, generateBook("&7Death Spamming", warn3lore));

        List<String> mute1lore = new java.util.ArrayList<>();
        mute1lore.add(Main.hex("#dc143c5m Tempmute"));
        inv.setItem(19, generateBook("&7Spamming/Repeating Phrases", mute1lore));

        List<String> mute2lore = new java.util.ArrayList<>();
        mute2lore.add(Main.hex("#dc143c5m Tempmute"));
        inv.setItem(20, generateBook("&7Excessive Swearing", mute2lore));

        List<String> mute3lore = new java.util.ArrayList<>();
        mute3lore.add(Main.hex("#dc143c30m Tempmute"));
        inv.setItem(21, generateBook("&7Inappropriate Messages", mute3lore));

        List<String> ban1lore = new java.util.ArrayList<>();
        ban1lore.add(Main.hex("#dc143c5d Tempban"));
        inv.setItem(28, generateBook("&7Hacking/Exploiting (1st Offense)", ban1lore));

        List<String> ban2lore = new java.util.ArrayList<>();
        ban2lore.add(Main.hex("#dc143cPerm Ban"));
        inv.setItem(29, generateBook("&7Hacking/Exploiting (2nd Offense)", ban2lore));

        List<String> ban3lore = new java.util.ArrayList<>();
        ban3lore.add(Main.hex("#dc143c5d Ban"));
        inv.setItem(30, generateBook("&7Griefing/Stealing (Minor)", ban3lore));

        List<String> ban4lore = new java.util.ArrayList<>();
        ban4lore.add(Main.hex("#dc143cPerm Ban"));
        inv.setItem(31, generateBook("&7Griefing/Stealing (MAJOR)", ban4lore));

        List<String> ban5lore = new java.util.ArrayList<>();
        ban5lore.add(Main.hex("#dc143cPerm IP Ban"));
        inv.setItem(32, generateBook("&7Bypassing Ban or Threatening Server", ban5lore));

        inv.setItem(7, generateItem(Material.PACKED_ICE, "#dc143cRollback Player Grief"));



        admin.openInventory(inv);
        return true;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e.getView().getTitle().contains(Main.hex("#dc143c&lPUNISH"))) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null) {
                String playerName = e.getView().getTitle().replace(Main.hex("#dc143c&lPUNISH" + " &f➠ #dc143c"), "");
                switch (e.getRawSlot()) {
                    case 4 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> admin.performCommand("retribution:punishments " + playerName), 4L);
                    }, 1L);
                    case 7 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("co rollback u:" + playerName + " t:1w r:global");
                    }, 1L);
                    case 10 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("warn " + playerName + " Spamming/Repeating Phrases");
                    }, 1L);
                    case 11 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("warn " + playerName + " Excessive Swearing");
                    }, 1L);
                    case 12 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("warn " + playerName + " Death Spamming/Dying on purpose repeatedly");
                    }, 1L);
                    case 19 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("mute " + playerName + " 5m Spamming/Repeating Phrases");
                    }, 1L);
                    case 20 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("mute " + playerName + " 5m Excessive Swearing");
                    }, 1L);
                    case 21 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("mute " + playerName + " 30m Inappropriate Messages");
                    }, 1L);
                    case 28 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("tempban " + playerName + " 5d Hacking/Exploiting (1st Offense)");
                    }, 1L);
                    case 29 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("ban " + playerName + " Hacking/Exploiting (2nd Offense)");
                    }, 1L);
                    case 30 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("tempban " + playerName + "5d Griefing/Stealing (Minor)");
                    }, 1L);
                    case 31 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("ban " + playerName + " Griefing/Stealing (Major)");
                    }, 1L);
                    case 32 -> Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, () -> {
                        Player admin = (Player) e.getWhoClicked();
                        e.getInventory().clear();
                        admin.closeInventory();
                        admin.performCommand("ipban -s " + playerName + " Bypassing Ban/Threatening Server");
                    }, 1L);
                }
            }
        }
    }
    public ItemStack generateBook(String title, @Nullable List<String> lore) {
        ItemStack book = new ItemStack(Material.PAPER);
        ItemMeta bookMeta = book.getItemMeta();
        assert bookMeta != null;
        bookMeta.setDisplayName(Main.hex(title));
        if(lore != null) {
            bookMeta.setLore(lore);
        }
        book.setItemMeta(bookMeta);
        return book;
    }
    public ItemStack generateLabel(String title) {
        ItemStack label = new ItemStack(Material.BOOK);
        ItemMeta labelMeta = label.getItemMeta();
        assert labelMeta != null;
        labelMeta.setDisplayName(Main.hex(title));
        label.setItemMeta(labelMeta);
        return label;
    }

    public ItemStack generateItem(Material m, String name) {
        ItemStack item = new ItemStack(m);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(Main.hex(name));
        item.setItemMeta(itemMeta);
        return item;
    }
}
