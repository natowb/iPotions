package me.natowb.ipotions.creator;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class PotionCreator {


    public static HashMap<UUID, PotionCreatorGUI> pendingPotions = new HashMap<>();

    public static final Map<String, Color> COLORS = new HashMap<String, Color>() {{
        put("red", Color.RED);
        put("green", Color.GREEN);
        put("blue", Color.BLUE);
        put("aqua", Color.AQUA);
        put("fuchsia", Color.FUCHSIA);
        put("white", Color.WHITE);
        put("yellow", Color.YELLOW);
        put("black", Color.BLACK);
        put("gray", Color.GRAY);
        put("lime", Color.LIME);
        put("maroon", Color.MAROON);
        put("navy", Color.NAVY);
        put("olive", Color.OLIVE);
        put("orange", Color.ORANGE);
        put("purple", Color.PURPLE);
        put("silver", Color.SILVER);
        put("teal", Color.TEAL);


    }};
    public static final Set<String> EFFECTS = new HashSet<String>() {{
        add("INVISIBILITY");
        add("JUMP");
        add("FIRE_RESISTANCE");
        add("SPEED");
        add("LUCK");
        add("HEAL");
        add("REGENERATION");
        add("SLOW");
        add("WATER_BREATHING");
        add("HARM");
        add("POISON");
        add("WEAKNESS");
        add("SLOW_FALLING");
        add("NIGHT_VISION");
        add("INCREASE_DAMAGE");
    }};

    private static JavaPlugin plugin;

    public static void init(JavaPlugin pl) {
        plugin = pl;
    }

    public static void ColorPotions(Player player) {
        for(String s : COLORS.keySet()) {
            ItemStack item = new ItemStack(Material.POTION);
            PotionMeta pmeta = (PotionMeta) item.getItemMeta();
            pmeta.setColor(COLORS.get(s));
            pmeta.setDisplayName(s);
            item.setItemMeta(pmeta);
            player.getInventory().addItem(item);
        }
    }

    public static boolean validPotion(String potion) {
        try {
            for(String s : plugin.getConfig().getConfigurationSection("potions").getKeys(false)) {
                if(s.equalsIgnoreCase(potion)) {
                    return true;
                }
            }
        }
        catch (NullPointerException e) {
            return false;
        }

        return false;
    }


    public static ArrayList<String> getPotions() {
        ArrayList<String> displays = new ArrayList<>();
        try {
            for(String s : plugin.getConfig().getConfigurationSection("potions").getKeys(false)) {
                    String display = plugin.getConfig().getString("potions."+s+".display");
                    displays.add(display);
            }
        }
        catch (NullPointerException e) {
            return displays;
        }


        return displays;
    }


    public static ItemStack createPotion(String potion, int amount) {
        ItemStack item = new ItemStack(Material.POTION, amount);
        PotionMeta pmeta = (PotionMeta) item.getItemMeta();

        try {
            for(String s : plugin.getConfig().getConfigurationSection("potions").getKeys(false)) {
                if(s.equalsIgnoreCase(potion)) {
                    String type = plugin.getConfig().getString("potions."+s+".type");
                    if(type.equalsIgnoreCase("splash")) {item = new ItemStack(Material.SPLASH_POTION, amount);}
                    if(type.equalsIgnoreCase("area")) {item = new ItemStack(Material.LINGERING_POTION, amount);}
                    String display = plugin.getConfig().getString("potions."+s+".display");
                    String color = plugin.getConfig().getString("potions."+s+".color");
                    pmeta.setColor(COLORS.get(color));
                    pmeta.setLore(Arrays.asList("iPot: " + s));
                    for(String e : plugin.getConfig().getConfigurationSection("potions."+s+".effects").getKeys(false)) {
                        String duration = plugin.getConfig().getString("potions."+s+".effects."+e+".duration");
                        String strength = plugin.getConfig().getString("potions."+s+".effects."+e+".strength");
                        if(PotionEffectType.getByName(e) == null) {
                            continue;
                        }
                        pmeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(e), (Integer.parseInt(duration) * 20), Integer.parseInt(strength)),true);
                    }
                    pmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', display));
                    item.setItemMeta(pmeta);
                }
            }
        }
        catch (NullPointerException e) {
            return null;
        }


        return item;
    }

}
