package org.rammex.moneyexpand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.rammex.moneyexpand.utils.MoneyUtils;

public final class MoneyExpand extends JavaPlugin {

    @Override
    public void onEnable() {
        message();

        MoneyUtils moneyUtils = new MoneyUtils(this);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new MoneyPlaceholders(this, moneyUtils).register();
        }
    }

    @Override
    public void onDisable() {

    }

    public void message(){
        getLogger().info("--------------------------");
        getLogger().info("MoneyExpand is enabled!");
        getLogger().info("Author: .rammex");
        getLogger().info("Version: 1.0");
        getLogger().info("--------------------------");
    }
}
