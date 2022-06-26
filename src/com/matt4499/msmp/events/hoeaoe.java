package com.matt4499.msmp.events;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.HashMap;
public class hoeaoe implements Listener {
    public HashMap<Player, Long> delay = new HashMap<>();
    @EventHandler
    public void onHarvest(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(delay.get(p) != null && System.currentTimeMillis() - delay.get(p) < 250) {
            return;
        }
        if(e.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if(e.getClickedBlock() == null) { return; }
        Material clicked = e.getClickedBlock().getType();
        ItemStack item = p.getInventory().getItemInMainHand();
        if(clicked == Material.WHEAT || clicked == Material.CARROTS || clicked == Material.POTATOES) {
            if(item.getType() == Material.NETHERITE_HOE) {
                if(item.getEnchantments().size() < 1) {
                    return;
                }
                if(item.getEnchantments().get(Enchantment.DIG_SPEED) != 5) {
                    return;
                }
                delay.put(p, System.currentTimeMillis());
                e.setCancelled(true);
                harvest3x3(e.getClickedBlock(), p);
            }
            if(item.getType() == Material.WOODEN_HOE || item.getType() == Material.STONE_HOE || item.getType() == Material.IRON_HOE || item.getType() == Material.DIAMOND_HOE || item.getType() == Material.GOLDEN_HOE) {
                e.setCancelled(true);
                delay.put(p, System.currentTimeMillis());
                Block b = e.getClickedBlock();
                Ageable crop = (Ageable) b.getBlockData();
                if(crop.getAge() == 7) {
                    crop.setAge(0);
                    b.setBlockData(crop);
                    p.playSound(p.getLocation(), "minecraft:block.crop.break", 1, 1);
                    switch(b.getType()) {
                        case WHEAT -> p.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
                        case CARROTS -> p.getInventory().addItem(new ItemStack(Material.CARROT, 1));
                        case POTATOES -> p.getInventory().addItem(new ItemStack(Material.POTATO, 1));
                    }
                    if(p.isSneaking()) {
                        if (b.getType() == Material.WHEAT) {
                            p.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, 1));
                        }
                    }
                    Damageable d = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
                    assert d != null;
                    d.setDamage(d.getDamage() + 1);
                    p.getInventory().getItemInMainHand().setItemMeta(d);
                    if(d.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
                        p.getInventory().setItemInMainHand(null);
                        p.playSound(p.getLocation(), "minecraft:entity.item.break", 1, 1);
                    }
                }
            }
        }
    }

    public void harvest3x3(Block center, Player p) {
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -1; z <= 1; z++) {
                    Block b = center.getRelative(x, y, z);
                    if(b.getType() == Material.WHEAT || b.getType() == Material.CARROTS || b.getType() == Material.POTATOES) {
                        Ageable crop = (Ageable) b.getBlockData();
                        if(crop.getAge() == 7) {
                            if(p.getInventory().getItemInMainHand().getType() != Material.NETHERITE_HOE) {
                                return;
                            }
                            crop.setAge(0);
                            b.setBlockData(crop);
                            p.playSound(p.getLocation(), "minecraft:block.crop.break", 1, 1);
                            switch(b.getType()) {
                                case WHEAT -> p.getInventory().addItem(new ItemStack(Material.WHEAT, 1));
                                case CARROTS -> p.getInventory().addItem(new ItemStack(Material.CARROT, 1));
                                case POTATOES -> p.getInventory().addItem(new ItemStack(Material.POTATO, 1));
                            }
                            if(p.isSneaking()) {
                                if (b.getType() == Material.WHEAT) {
                                    p.getInventory().addItem(new ItemStack(Material.WHEAT_SEEDS, 1));
                                }
                            }
                            Damageable d = (Damageable) p.getInventory().getItemInMainHand().getItemMeta();
                            assert d != null;
                            d.setDamage(d.getDamage() + 1);
                            p.getInventory().getItemInMainHand().setItemMeta(d);
                            if(d.getDamage() >= p.getInventory().getItemInMainHand().getType().getMaxDurability()) {
                                p.getInventory().setItemInMainHand(null);
                                p.playSound(p.getLocation(), "minecraft:entity.item.break", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }
}
