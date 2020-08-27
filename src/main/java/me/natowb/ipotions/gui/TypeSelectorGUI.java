package me.natowb.ipotions.gui;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.iPotions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TypeSelectorGUI {

    public TypeSelectorGUI (Player player, String potionName) {



        ItemStack item1 = new ItemStack(Material.POTION);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(Lib.color(potionName));
        meta1.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        item1.setItemMeta(meta1);

        // Log Item
        // TODO implement this menu
        ItemStack item2 = new ItemStack(Material.SPLASH_POTION);
        ItemMeta meta2= item2.getItemMeta();
        meta2.setDisplayName(Lib.color(potionName));
        meta2.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        item2.setItemMeta(meta2);
        // Stats Item
        // TODO implement this menu
        ItemStack item3 = new ItemStack(Material.LINGERING_POTION);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(Lib.color(potionName));
        meta3.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item3.setItemMeta(meta3);


        Inventory gui = Bukkit.createInventory(player, 27, "iPotions: Select Type");
        gui.setItem(11, item1);
        gui.setItem(13, item2);
        gui.setItem(15, item3);
        player.openInventory(gui);

    }

}
