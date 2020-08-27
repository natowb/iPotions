package me.natowb.ipotions.gui;

import me.natowb.ipotions.Lib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

public class InfoGUI {

    public InfoGUI(Player player,  ItemStack item) {
        Inventory gui = Bukkit.createInventory(player, 54, "iPotions: Manage Effects");

        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setLore(Arrays.asList(Lib.color("&a[Click to Create]")));
        meta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);

        int i = 0;
        for(PotionType type : Lib.EFFECTS.keySet()) {

            boolean hasEffect = true;
            for(PotionEffectType pet : Lib.EFFECTS.get(type)) {
                if(!meta.hasCustomEffect(pet)) {
                    hasEffect = false;
                }
            }

            ItemStack item1 = new ItemStack(Material.POTION);
            PotionMeta meta1 = (PotionMeta)item1.getItemMeta();
            meta1.setBasePotionData(new PotionData(type));
            meta1.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            if(hasEffect) {
                meta1.setLore(Arrays.asList(
                        Lib.color("&aAdded"),
                        Lib.color("&c[Click to Remove]")
                ));
            } else {
                meta1.setLore(Arrays.asList(
                        Lib.color("&cNot Added"),
                        Lib.color("&a[Click to Add]")
                ));
            }
            item1.setItemMeta(meta1);
            gui.setItem(18+i, item1);
            i +=1;
        }




        gui.setItem(4, item);
        player.openInventory(gui);

    }

}
