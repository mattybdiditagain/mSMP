package com.matt4499.msmp.events;

import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
public class CrateHandler implements Listener {
    public ArrayList<ItemStack> prizes = new ArrayList<>();
    public CrateHandler() {
        HashMap<Enchantment, Integer> knockusBackusEnchants = new HashMap<>();
        knockusBackusEnchants.put(Enchantment.KNOCKBACK, 5);
        prizes.add(generateItem(Material.STICK, 1, true, knockusBackusEnchants, "&4Knockus Backus"));
        prizes.add(new ItemStack(Material.IRON_INGOT, 8));
        prizes.add(new ItemStack(Material.GOLD_INGOT, 8));
        prizes.add(new ItemStack(Material.DIAMOND, 2));
        prizes.add(new ItemStack(Material.EMERALD, 1));
        prizes.add(new ItemStack(Material.COOKED_BEEF, 16));
        prizes.add(new ItemStack(Material.COOKED_CHICKEN, 16));
        prizes.add(new ItemStack(Material.COOKED_SALMON, 16));
        prizes.add(new ItemStack(Material.TNT, 8));
        prizes.add(new ItemStack(Material.ARROW, 16));
        prizes.add(new ItemStack(Material.GUNPOWDER, 8));
        prizes.add(new ItemStack(Material.BRICKS, 64));
        prizes.add(new ItemStack(Material.COBBLESTONE, 64));
        prizes.add(new ItemStack(Material.SAND, 64));
        prizes.add(new ItemStack(Material.GLASS, 64));
        prizes.add(new ItemStack(Material.STONE_BRICKS, 64));
        prizes.add(new ItemStack(Material.OAK_LOG, 64));
        prizes.add(new ItemStack(Material.STONE, 64));
        prizes.add(generateItem(Material.CHEST, 1, false, null, "&aVote Crate"));
    }

    public ItemStack getRandomItem() {
        int randInd = (int) (Math.random() * prizes.size());
        return prizes.get(randInd);
    }

    public static ItemStack generateItem(Material m, int count, Boolean enchanted, HashMap<Enchantment, Integer> enchants, String customname) {
        ItemStack item = new ItemStack(m);
        ItemMeta im = item.getItemMeta();
        item.setAmount(count);
        if(customname.contains("&4Knockus Backus")) {
            ArrayList<String> lore = new ArrayList<>();
            lore.add(Main.hex("§4Uses: 10"));
            assert im != null;
            im.setLore(lore);
        }
        if(enchanted) {
            for(Enchantment e : enchants.keySet()) {
                int level = enchants.get(e);
                assert im != null;
                im.addEnchant(e, level, true);
            }
        }
        assert im != null;
        im.setDisplayName(Main.hex(customname));
        item.setItemMeta(im);
        return item;
    }

    public void giveRandomItem(Player player) {
        ItemStack won = getRandomItem();
        player.getInventory().addItem(won);
        ItemMeta im = won.getItemMeta();
        String name;
        if(im == null || !im.hasDisplayName()) {
            name = won.getType().getKey().getKey();
        } else {
            name = im.getDisplayName();
        }

        if(player.getInventory().firstEmpty() == -1) {
            player.sendMessage(Main.hex("#dc143cYou don't have enough space! Prize has been dropped on the ground."));
            player.getWorld().dropItem(player.getLocation(), won);
        }

        Bukkit.broadcastMessage(Main.hex("#dc143c&lCRATES &7➠ &7" + player.getName() + " &7has won #dc143c" +  won.getAmount() + "x #dc143c" + name + " &7from a #dc143c/vote &7crate!"));
        Main.logToChatChannel("**CRATES:** " + player.getName() + " has won ***" + won.getAmount() + "x " + uncolor(name) + "*** from a /vote crate!");
        Main.logToGameLogs("[Crates] " + player.getName() + " has won " + won.getAmount() + "x " + uncolor(name) + " from a /vote crate!");
    }

    public String uncolor(String s) {
        String fixed = s.replaceAll("§([0-9a-f])", "");
        return fixed.replaceAll("&([a-f0-9])", "");
    }

    @EventHandler
    public void onCrateRightClick(PlayerInteractEvent e){
        if(e.getItem() == null) return;
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getItem().getType() == Material.CHEST) {
                ItemMeta im = e.getItem().getItemMeta();
                if (im == null) return;
                if (im.getDisplayName().contains(Main.hex("&aVote Crate"))) {
                    e.setCancelled(true);
                    openCrate(e.getPlayer());
                } else {
                    Bukkit.getConsoleSender().sendMessage("name: " + im.getDisplayName());
                }
            }
        }
    }

    public void openCrate(Player p) {
            p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
            giveRandomItem(p);
    }

    public static void givePlayerVoteCrate(Player player) {
        if(player.getInventory().firstEmpty() == -1) {
            player.sendMessage(Main.hex("#dc143cYou don't have enough space in your inventory!"));
            return;
        }
        player.getInventory().addItem(generateItem(Material.CHEST, 1, false, null, "&aVote Crate"));
    }

}
