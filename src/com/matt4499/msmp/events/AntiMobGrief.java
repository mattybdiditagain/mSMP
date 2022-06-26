package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
public class AntiMobGrief implements Listener {
    @EventHandler
    public void onMobGrief(EntityChangeBlockEvent e) {
        if(e.getEntity() instanceof Enderman) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onCreeper(EntityExplodeEvent e) {
        if(e.getEntity() instanceof Creeper) {
            e.blockList().clear();
        }
    }
    @EventHandler
    public void disablePhantom(EntitySpawnEvent e) {
        if(e.getEntity() instanceof Phantom) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onShulkerDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Shulker) {
            event.getDrops().clear();
            ItemStack shell = new ItemStack(Material.SHULKER_SHELL);
            event.getDrops().add(shell);
            event.getDrops().add(shell); //add 2 shells when a shulker is killed
        }
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            if(e.getEntity() instanceof Wolf || e.getEntity() instanceof Horse || e.getEntity() instanceof Parrot || e.getEntity() instanceof Cat || e.getEntity() instanceof SkeletonHorse || e.getEntity() instanceof Llama || e.getEntity() instanceof Donkey || e.getEntity() instanceof Mule) {
                Player player = (Player) e.getDamager();
                Tameable tameable = (Tameable) e.getEntity();
                if(tameable.isTamed()) {
                    if(tameable.getOwner() != player) {
                        e.setCancelled(true);
                        player.sendMessage(Main.hex("#dc143c&lSERVER &f➠ #dc143cYou cannot damage " + tameable.getName() + " because they're owned by " + Objects.requireNonNull(tameable.getOwner()).getName()));
                    }
                }
            }
        }
    }
}
