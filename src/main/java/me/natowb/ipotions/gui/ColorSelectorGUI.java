package me.natowb.ipotions.gui;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.Arrays;

public class ColorSelectorGUI {

    public ColorSelectorGUI(Player player, String potionName, String type) {
        Inventory gui = Bukkit.createInventory(player, 36, "iPotions: Select Color");
        String pType = " &bPotion";
        ItemStack item1 = new ItemStack(Material.POTION);
        PotionMeta meta1 = (PotionMeta)item1.getItemMeta();
        if(type == "splash") {pType=" &bSplash Potion";item1 = new ItemStack(Material.SPLASH_POTION);}
        if(type == "area") {pType=" &bLingering Potion";item1 = new ItemStack(Material.LINGERING_POTION);}
        for(String color : PotionManager.Colors.keySet()) {
            String c = color.substring(0,1).toUpperCase();
            String olor = color.substring(1,color.length());
            meta1.setDisplayName(Lib.color(potionName));
            meta1.setColor(PotionManager.Colors.get(color));
            meta1.setLore(Arrays.asList(Lib.color("&b"+c+olor)));
            meta1.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            item1.setItemMeta(meta1);
            gui.addItem(item1);
        }

        player.openInventory(gui);

    }

}
