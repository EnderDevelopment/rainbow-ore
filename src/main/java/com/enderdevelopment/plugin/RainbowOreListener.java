package com.enderdevelopment.plugin;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainbowOreListener implements Listener {
    private static final String LOCATION_PATH = "rainbow-ore-locations";
    private static final List<Material> DROP_MATERIALS = List.of(
            Material.DIAMOND, Material.IRON_INGOT, Material.RAW_IRON, Material.COPPER_INGOT,
            Material.RAW_COPPER, Material.GOLD_INGOT, Material.RAW_GOLD, Material.EMERALD,
            Material.COAL, Material.QUARTZ);

    private final JavaPlugin plugin;
    private final NamespacedKey rainbowOreKey;

    public RainbowOreListener(JavaPlugin plugin, NamespacedKey rainbowOreKey) {
        this.plugin = plugin;
        this.rainbowOreKey = rainbowOreKey;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (!isRainbowOre(item)) {
            return;
        }
        saveLocation(event.getBlockPlaced().getLocation());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        if (!isTracked(location)) {
            return;
        }
        event.setDropItems(false);
        removeLocation(location);
        Material drop = DROP_MATERIALS.get(ThreadLocalRandom.current().nextInt(DROP_MATERIALS.size()));
        event.getBlock().getWorld().dropItemNaturally(location, new ItemStack(drop));
    }

    private boolean isRainbowOre(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        Byte marker = item.getItemMeta().getPersistentDataContainer().get(rainbowOreKey, PersistentDataType.BYTE);
        return marker != null && marker == (byte) 1;
    }

    private String locationKey(Location location) {
        return location.getWorld().getUID() + ":" + location.getBlockX() + ":" + location.getBlockY() + ":" + location.getBlockZ();
    }

    private void saveLocation(Location location) {
        List<String> locations = plugin.getConfig().getStringList(LOCATION_PATH);
        String key = locationKey(location);
        if (!locations.contains(key)) {
            locations.add(key);
            plugin.getConfig().set(LOCATION_PATH, locations);
            plugin.saveConfig();
        }
    }

    private boolean isTracked(Location location) {
        return plugin.getConfig().getStringList(LOCATION_PATH).contains(locationKey(location));
    }

    private void removeLocation(Location location) {
        List<String> locations = plugin.getConfig().getStringList(LOCATION_PATH);
        locations.remove(locationKey(location));
        plugin.getConfig().set(LOCATION_PATH, locations);
        plugin.saveConfig();
    }
}
