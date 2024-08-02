package org.rammex.moneyexpand;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.rammex.moneyexpand.utils.MoneyUtils;

import java.math.BigDecimal;

public class MoneyPlaceholders extends PlaceholderExpansion {

    private final MoneyExpand plugin;
    private final MoneyUtils moneyUtils;

    public MoneyPlaceholders(MoneyExpand plugin, MoneyUtils moneyUtils) {
        this.plugin = plugin;
        this.moneyUtils = moneyUtils;
    }

    public String getBankBalance(Player player) {
        Island island = SuperiorSkyblockAPI.getPlayer(player).getIsland();
        if (island != null) {
            BigDecimal balance = island.getIslandBank().getBalance();
            return moneyUtils.formatNumber(balance.doubleValue());
        } else {
            return "No island";
        }
    }

    @Override
    public String getIdentifier() {
        return "Expand";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }

        // %Expand_show_money%
        if (identifier.equals("show_money")) {
            try {
                double money = moneyUtils.getBalance(player);
                return moneyUtils.formatNumber(money);
            } catch (NullPointerException e) {
                plugin.getLogger().info("Error: " + e);
                return "0";
            }

        }

        // %Expand_show_isbank%
        if (identifier.equals("show_isbank")) {
            try {
                return getBankBalance(player);
            } catch (NullPointerException e) {
                plugin.getLogger().info("Error: " + e);
                return "0";
            }

        }

        return null;
    }
}