package org.rammex.moneyexpand.utils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.rammex.moneyexpand.MoneyExpand;


public class MoneyUtils {
    MoneyExpand plugin;
    private Economy econ = null;

    public MoneyUtils(MoneyExpand plugin) {
        this.plugin = plugin;
        if (!setupEconomy() ) {
            Bukkit.getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", Bukkit.getPluginManager().getPlugin("MoneyExpand").getDescription().getName()));
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("MoneyExpand"));
            return;
        }
    }

    public String formatNumber(double number) {
        if (number >= 1_000_000_000) {
            long roundedNumber = Math.round(number);
            return String.format("%dmd", roundedNumber / 1_000_000_000);
        } else if (number >= 1_000_000) {
            long roundedNumber = Math.round(number);
            return String.format("%dm", roundedNumber / 1_000_000);
        } else if (number >= 1_000) {
            long roundedNumber = Math.round(number);
            return String.format("%dk", roundedNumber / 1_000);
        } else {
            long roundedNumber = Math.round(number);
            return String.valueOf(roundedNumber);
        }
    }


    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public double getBalance(Player player) {
        return econ.getBalance(player);
    }
}
