package me.natowb.ipotions.creator;

import me.natowb.ipotions.Lib;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PotionCreatorGUI implements Listener {


    private JavaPlugin plugin;


    public String name;
    public String display;
    public String color;

    public PotionCreatorGUI(JavaPlugin plugin, Player player, String name, String display, String color) {
        this.plugin = plugin;
        this.name = name;
        this.display = ChatColor.translateAlternateColorCodes('&', display);
        this.color = color;
        PotionCreator.pendingPotions.put(player.getUniqueId(), this);
        showGUI(player);

    }


    public void showGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 9, "Set Potion Effects");
        player.openInventory(gui);
    }


}
