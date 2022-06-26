package com.matt4499.msmp.commands;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
public class ShowCommand implements CommandExecutor {
    public static HashMap<Player, Long> cooldown = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand.getType() == Material.AIR) {
            player.sendMessage(Main.hex("#dc143c&lSERVER &f➠ #dc143cYou must be holding an item to use this command."));
        } else {
            if (cooldown.containsKey(player)) {
                if (System.currentTimeMillis() - cooldown.get(player) < 5000) {
                    player.sendMessage(Main.hex("#dc143c&lSERVER &f➠ #dc143cYou must wait 5 seconds before using this command again."));
                    return true;
                }
            }
            cooldown.put(player, System.currentTimeMillis());
            String playerName = player.getName();
            String itemName = itemInMainHand.getType().getKey().getKey();
            int amount = itemInMainHand.getAmount();
            boolean isEnchanted = !itemInMainHand.getEnchantments().isEmpty();
            if (isEnchanted) {
                StringBuilder enchantments = new StringBuilder();
                for (Enchantment e : itemInMainHand.getEnchantments().keySet()) {
                    enchantments.append(e.getKey().getKey()).append(" ").append(itemInMainHand.getEnchantmentLevel(e)).append(", ");
                }
                enchantments.delete(enchantments.length() - 2, enchantments.length());
                Bukkit.broadcastMessage(Main.hex("#dc143c&lSERVER &f➠ &e" + playerName + " &7shows: &e" + itemName + " &7with enchants: &e" + enchantments));
                Main.logToChatChannel("[Show] " + playerName + " showed " + itemName + " with enchants: "  + enchantments);
            } else {
                Bukkit.broadcastMessage(Main.hex("#dc143c&lSERVER &f➠ &e" + playerName + " &7shows &e" + amount + "x &e" + itemName));
                Main.logToChatChannel("[Show] " + playerName + " showed " + amount +"x " + itemName);
            }
        }
        return true;
    }
}
