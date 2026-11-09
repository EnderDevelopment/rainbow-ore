package com.enderdevelopment.plugin;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private NamespacedKey rainbowOreKey;

    @Override
    public void onEnable() {
        rainbowOreKey = new NamespacedKey(this, "rainbow_ore");
        RainbowOreListener listener = new RainbowOreListener(this, rainbowOreKey);
        getServer().getPluginManager().registerEvents(listener, this);
        RainbowOreCommand command = new RainbowOreCommand(rainbowOreKey);
        getCommand("rainbowore").setExecutor(command);
        getCommand("rainbowore").setTabCompleter(command);
    }

    public NamespacedKey getRainbowOreKey() {
        return rainbowOreKey;
    }
}
