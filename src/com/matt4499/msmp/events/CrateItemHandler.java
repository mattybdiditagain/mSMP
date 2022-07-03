package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
public class CrateItemHandler implements Listener {

    @EventHandler
    public void onKnockusBackusUse(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player p) {
            if (!(event.getCause() == EntityDamageByEntityEvent.DamageCause.ENTITY_ATTACK)) { return; }
            ItemStack inHand = p.getInventory().getItemInMainHand();
            ItemMeta itemMeta = inHand.getItemMeta();
            if(itemMeta == null) { return; }
            if (itemMeta.getDisplayName().contains("Knockus Backus") && inHand.getType() == Material.STICK) {
                List<String> lore = itemMeta.getLore();
                if(lore == null) {
                    p.getInventory().setItemInMainHand(null);
                    return;
                }
                for(String line: lore) {
                    if(line.contains("Uses: ")) {
                        int usesInt = Integer.parseInt(line.replace("§4Uses: ", ""));
                        if (usesInt > 1) {
                            usesInt -= 1;
                            itemMeta.setLore(List.of(Main.hex("&4Uses: " + usesInt)));
                            inHand.setItemMeta(itemMeta);
                            p.sendMessage(Main.hex("&7You used #dc143cKnockus Backus &7and have #dc143c" + usesInt + " &7uses left!"));
                            p.playSound(p.getLocation(), Sound.ITEM_CROSSBOW_HIT,1.0F, 1.0F);
                        } else {
                            p.getInventory().setItemInMainHand(null);
                            p.sendMessage(Main.hex("#dc143cYou have no uses left!"));
                            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                        }
                    }
                }

            }
        }
    }
}
