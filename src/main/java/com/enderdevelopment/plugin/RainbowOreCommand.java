package com.enderdevelopment.plugin;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class RainbowOreCommand implements CommandExecutor, TabCompleter {
    private final NamespacedKey rainbowOreKey;

    public RainbowOreCommand(NamespacedKey rainbowOreKey) {
        this.rainbowOreKey = rainbowOreKey;
    }

    public static ItemStack createItem(NamespacedKey key, int amount) {
        ItemStack item = new ItemStack(Material.AMETHYST_BLOCK, amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("Rainbow Ore").color(NamedTextColor.LIGHT_PURPLE));
        meta.lore(List.of(Component.text("Break it to discover a random resource.").color(NamedTextColor.GRAY)));
        meta.getPersistentDataContainer().set(key, org.bukkit.persistence.PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can receive rainbow ore.");
            return true;
        }
        int amount = 1;
        if (args.length > 1) {
            player.sendMessage(Component.text("Usage: /rainbowore [amount]").color(NamedTextColor.RED));
            return true;
        }
        if (args.length == 1) {
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException exception) {
                player.sendMessage(Component.text("Amount must be a whole number from 1 to 64.").color(NamedTextColor.RED));
                return true;
            }
            if (amount < 1 || amount > 64) {
                player.sendMessage(Component.text("Amount must be a whole number from 1 to 64.").color(NamedTextColor.RED));
                return true;
            }
        }
        player.getInventory().addItem(createItem(rainbowOreKey, amount));
        player.sendMessage(Component.text("You received " + amount + " Rainbow Ore.").color(NamedTextColor.LIGHT_PURPLE));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return args.length == 1 ? List.of("1", "8", "16", "32", "64") : List.of();
    }
}
