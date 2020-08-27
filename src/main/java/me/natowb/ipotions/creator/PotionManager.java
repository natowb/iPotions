package me.natowb.ipotions.creator;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.data.PendingPotion;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Array;
import java.util.*;

public class PotionManager {



    public static final Map<String, Color> Colors = new HashMap<String, Color>() {{
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

    public static final Map<Color, String> ColorNames = new HashMap<Color, String>() {{
        put(Color.RED,"red");
        put(Color.GREEN,"green" );
        put(Color.BLUE,"blue" );
        put(Color.AQUA,"aqua" );
        put(Color.FUCHSIA,"fuchsia" );
        put(Color.WHITE,"white" );
        put(Color.YELLOW,"yellow");
        put(Color.BLACK,"black" );
        put(Color.GRAY,"gray" );
        put(Color.LIME,"lime" );
        put( Color.MAROON,"maroon");
        put(Color.NAVY,"navy" );
        put(Color.OLIVE,"olive");
        put( Color.ORANGE,"orange");
        put(Color.PURPLE,"purple" );
        put(Color.SILVER,"silver");
        put(Color.TEAL,"teal");
    }};




    public static final List<String> EFFECTS = new ArrayList<String>() {{
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




    private JavaPlugin plugin;
    public static HashMap<String, ItemStack> potions = new HashMap<>();
    public static HashMap<UUID, PendingPotion> pendingPotions = new HashMap<>();

    private int duration = 10;
    private int strength = 1;


    public PotionManager(JavaPlugin plugin) {
        this.plugin = plugin;

        try {
            duration = plugin.getConfig().getInt("duration_default");
            strength = plugin.getConfig().getInt("strength_default");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> getPotionList() {
        return new ArrayList<>(potions.keySet());
    }

    public static boolean validPotion(String potion) {
        return (potions.keySet().contains(potion.toLowerCase()));
    }

    public static ItemStack getPotion(String potion, int amount) {
        ItemStack item = potions.get(potion.toLowerCase());
        if(item==null) {
            return null;
        }
        item.setAmount(amount);
        return item;
    }

    public boolean deletePotion(String potion) {
        if(validPotion(potion)) {
            potions.remove(potion);
            savePotions();
            return true;
        }

        return false;

    }

    public boolean editPotionType(Player player, String potName, String type) {

        ItemStack pot = PotionManager.potions.get(potName);
        if(pot == null) {
            return false;
        }
        switch (type) {
            case "base":
                pot.setType(Material.POTION);
                break;
            case "splash":
                pot.setType(Material.SPLASH_POTION);
                break;
            case "area":
                pot.setType(Material.LINGERING_POTION);
                break;
        }
        potions.put(potName, pot);
        return true;
    }

    public boolean editPotionColor(Player player, String potName, Color color) {

        ItemStack pot = PotionManager.potions.get(potName);
        if(pot == null) {
            return false;
        }
        PotionMeta meta = (PotionMeta) pot.getItemMeta();
        meta.setColor(color);
        pot.setItemMeta(meta);
        potions.put(potName, pot);
        return true;
    }




    public boolean createPotion(Player player , ItemStack potion) {
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        String potName = meta.getDisplayName();
        potName = potName.replace(" Lingering Potion", "");
        potName = potName.replace(" Splash Potion", "");
        potName = potName.replace(" §bPotion", "");
        String name = ChatColor.stripColor(potName).toLowerCase().replaceAll("\\s+", "");;
        PotionManager.potions.put(name.toLowerCase(), potion);
        return  true;
    }


    public void loadPotions() {
        FileConfiguration config = plugin.getConfig();
        try {
            for(String potion : config.getConfigurationSection("potions").getKeys(false)) {
                ItemStack pItem = new ItemStack(Material.POTION);
                PotionMeta pMeta = (PotionMeta) pItem.getItemMeta();
                String pType = config.getString("potions."+potion+".type");
                if(pType.equalsIgnoreCase("splash")) {
                    pItem = new ItemStack(Material.SPLASH_POTION);
                    pMeta = (PotionMeta) pItem.getItemMeta();
                }
                if(pType.equalsIgnoreCase("area")) {
                    pItem = new ItemStack(Material.LINGERING_POTION);
                    pMeta = (PotionMeta) pItem.getItemMeta();
                }
                String pItemDisplay = config.getString("potions."+potion+".display");
                String pItemColor = config.getString("potions."+potion+".color");
                ArrayList<String> possibleEffects = new ArrayList<>();
                for(String effect : config.getConfigurationSection("potions."+potion+".effects").getKeys(false)) {
                    String duration = config.getString("potions."+potion+".effects."+effect+".duration");
                    String strength = config.getString("potions."+potion+".effects."+effect+".strength");
                    possibleEffects.add(effect+";"+duration+";"+strength);
                }
                for(String s : possibleEffects) {
                    String[] split = s.split(";");

                    PotionEffectType type = PotionEffectType.getByName(split[0].toUpperCase());
                    if(type==null) {
                        Lib.msg(plugin.getServer().getConsoleSender(), "Error no effects");
                        continue;
                    }
                    pMeta.addCustomEffect(new PotionEffect(type, (Integer.parseInt(split[1])*20), Integer.parseInt(split[2])),true);
                }
                pMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', pItemDisplay));
                pMeta.setLore(Arrays.asList("iPot: "+potion));
                if(Colors.containsKey(pItemColor)) {
                    pMeta.setColor(Colors.get(pItemColor));
                }
                pItem.setItemMeta(pMeta);
                potions.put(potion.toLowerCase(), pItem);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            Lib.msg(plugin.getServer().getConsoleSender(), "&6No potions found");
        }

        Lib.msg(plugin.getServer().getConsoleSender(), "&aFinished loading potions");
    }


    public void savePotions() {
        for(String pName : PotionManager.potions.keySet()) {
            ItemStack potion = PotionManager.potions.get(pName);
            PotionMeta meta = (PotionMeta) potion.getItemMeta();
            String potName = meta.getDisplayName();
            potName = potName.replace(" Lingering Potion", "");
            potName = potName.replace(" Splash Potion", "");
            potName = potName.replace(" §bPotion", "");
            String name = ChatColor.stripColor(potName).toLowerCase().replaceAll("\\s+", "");;
            String display = potName;
            String color = ColorNames.get(meta.getColor());
            String type = "base";
            if(potion.getType() == Material.SPLASH_POTION) {type = "splash";}
            if(potion.getType() == Material.LINGERING_POTION) {type = "area";}

            plugin.getConfig().set("potions."+name+".display", display);
            plugin.getConfig().set("potions."+name+".color", color);
            plugin.getConfig().set("potions."+name+".type", type);

            for(PotionEffect effect : meta.getCustomEffects()) {
                plugin.getConfig().set("potions."+name+".effects."+effect.getType().getName().toLowerCase()+".duration", (effect.getDuration()/20));
                plugin.getConfig().set("potions."+name+".effects."+effect.getType().getName().toLowerCase()+".strength", effect.getAmplifier());
            }
            plugin.getConfig().options().copyHeader(true);
            plugin.getConfig().options().header(Lib.configHeader());
            plugin.saveConfig();
            Lib.msg(plugin.getServer().getConsoleSender(), "&aFinished saving potions");
        }
    }


}
