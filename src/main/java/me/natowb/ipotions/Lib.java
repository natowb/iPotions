package me.natowb.ipotions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Lib {

    private static JavaPlugin plugin;

    public static void init(JavaPlugin pl) {
        plugin = pl;
    }

    public static void msg(Player player, String message) {
        String pre = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
        String msg = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(pre + " " +msg);
    }


}
