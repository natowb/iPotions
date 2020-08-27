package me.natowb.ipotions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.*;

public class Lib {
    public static final Map<PotionType, HashSet<PotionEffectType>> EFFECTS = new HashMap<PotionType, HashSet<PotionEffectType>>() {{
        put(PotionType.FIRE_RESISTANCE, new HashSet<>(Arrays.asList(PotionEffectType.FIRE_RESISTANCE)));
        put(PotionType.INSTANT_DAMAGE, new HashSet<>(Arrays.asList(PotionEffectType.HARM)));
        put(PotionType.INSTANT_HEAL, new HashSet<>(Arrays.asList(PotionEffectType.HEAL)));
        put(PotionType.INVISIBILITY, new HashSet<>(Arrays.asList(PotionEffectType.INVISIBILITY)));
        put(PotionType.JUMP, new HashSet<>(Arrays.asList(PotionEffectType.JUMP)));
        put(PotionType.LUCK, new HashSet<>(Arrays.asList(PotionEffectType.LUCK)));
        put(PotionType.NIGHT_VISION, new HashSet<>(Arrays.asList(PotionEffectType.NIGHT_VISION)));
        put(PotionType.POISON, new HashSet<>(Arrays.asList(PotionEffectType.POISON)));
        put(PotionType.REGEN, new HashSet<>(Arrays.asList(PotionEffectType.REGENERATION)));
        put(PotionType.SLOW_FALLING, new HashSet<>(Arrays.asList(PotionEffectType.SLOW_FALLING)));
        put(PotionType.SLOWNESS, new HashSet<>(Arrays.asList(PotionEffectType.SLOW)));
        put(PotionType.SPEED, new HashSet<>(Arrays.asList(PotionEffectType.SPEED)));
        put(PotionType.STRENGTH, new HashSet<>(Arrays.asList(PotionEffectType.INCREASE_DAMAGE)));
        put(PotionType.WATER_BREATHING, new HashSet<>(Arrays.asList(PotionEffectType.WATER_BREATHING)));
        put(PotionType.WEAKNESS, new HashSet<>(Arrays.asList(PotionEffectType.WEAKNESS)));
    }};



    public static void msg(CommandSender player, String message) {
        String pre = color("[&6i&ePotions&r]");
        String msg = color(message);
        player.sendMessage(pre + " " +msg);
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String convertToInvisibleString(String s) {
        String hidden = "";
        for (char c : s.toCharArray()) hidden += ChatColor.COLOR_CHAR+""+c;
        return hidden;
    }

    public static String convertToVisibleString(String s) {
        String visible = s.replaceAll("§", "");
        return visible;
    }


    public static String configHeader() {
        String string =
                "##########################################################################\n" +
                "Created by NatoWB\n"+
                "https://github.com/natowb\n"+
                "##########################################################################\n"+
                "Potions\n"+
                "Potion Colors: lime, green, olive, yellow, orange, red, maroon\n"+
                "fuchsia, purple, aqua, teal, blue, navy, white, silver, gray, black\n"+
                "##########################################################################";
        return string;
    }






}
