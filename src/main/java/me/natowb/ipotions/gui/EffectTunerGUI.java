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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;

public class EffectTunerGUI {

    public EffectTunerGUI(Player player,ItemStack potion,  ItemStack potionEffect) {

        Inventory gui = Bukkit.createInventory(player, 45, "iPotions: Tune Effect");

        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
        PotionMeta meta = (PotionMeta) potionEffect.getItemMeta();


        ItemStack display = new ItemStack(Material.POTION);
        PotionMeta displayMeta= (PotionMeta) display.getItemMeta();
        displayMeta.setBasePotionData(new PotionData(PotionType.MUNDANE));
        displayMeta.setDisplayName(Lib.color("&a[ Add Effect ]"));
        displayMeta.setColor(meta.getColor());
        displayMeta.setLore(Arrays.asList(potionMeta.getDisplayName()));
        displayMeta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        for(PotionEffectType pet : Lib.EFFECTS.get(meta.getBasePotionData().getType())) {
            displayMeta.addCustomEffect(new PotionEffect(pet, 100, 0), true);
        }
        display.setItemMeta(displayMeta);

        potionMeta.setLore(Arrays.asList(potionMeta.getDisplayName()));
        potionMeta.setDisplayName(Lib.color("&c[ Back ]"));
        potionEffect.setItemMeta(meta);
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        potion.setItemMeta(potionMeta);
        gui.setItem(4,potion);



        // Log Item
        // TODO implement this menu
        ItemStack durationUp = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta durationUpMeta= durationUp.getItemMeta();
        durationUpMeta.setDisplayName(Lib.color("&eDuration: &a+5"));
        durationUp.setItemMeta(durationUpMeta);

        ItemStack durationDown = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta durationDownMeta= durationDown.getItemMeta();
        durationDownMeta.setDisplayName(Lib.color("&eDuration: &c-5"));
        durationDown.setItemMeta(durationDownMeta);

        ItemStack strengthUp = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta strengthUpMeta= strengthUp.getItemMeta();
        strengthUpMeta.setDisplayName(Lib.color("&eStrength: &a+1"));
        strengthUp.setItemMeta(strengthUpMeta);

        ItemStack strengthDown = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta strengthDownMeta= strengthDown.getItemMeta();
        strengthDownMeta.setDisplayName(Lib.color("&eStrength: &c-1"));
        strengthDown.setItemMeta(strengthDownMeta);
        // Stats Item
        // TODO implement this menu
        gui.setItem(19, durationDown);
        gui.setItem(20, durationUp);

        gui.setItem(24, strengthDown);
        gui.setItem(25, strengthUp);

        gui.setItem(22, display);
        player.openInventory(gui);

    }

}
