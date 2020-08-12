package me.natowb.ipotions;

import me.natowb.ipotions.commands.*;
import me.natowb.ipotions.creator.PotionCreator;
import me.natowb.ipotions.creator.PotionCreatorGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin implements Listener, CommandExecutor {


    public static Main ins;



    @Override
    public void onEnable() {
        // Plugin startup logic
        ins = this;
        setupConfig();
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("ipot").setExecutor(this);
        getCommand("ipot").setTabCompleter(new TabCompletion());
        Lib.init(this);
        PotionCreator.init(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupConfig() {
        saveDefaultConfig();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length>0) {
                if(args[0].equalsIgnoreCase("help")) {
                    new HelpCommand(player);
                    return true;
                }
                if(args.length==2) {
                    if(args[0].equalsIgnoreCase("delete")) {
                        new DeleteCommand(player, args);
                        return true;
                    }
                }
                if(args.length>=3) {
                    //give <player> <potion> [amount]
                    if(args[0].equalsIgnoreCase("give")) {
                        new GiveCommand(player, args);
                        return true;
                    }
                    //create <name> <display> <color>
                    if(args.length > 3&&args[0].equalsIgnoreCase("create")) {
                        new CreateCommand(player, args);
                        return true;
                    }
                    //edit <name> <attribute> <id> <value>
                    if(args.length > 3&&args[0].equalsIgnoreCase("edit")) {
                        new EditCommand(player, args);
                        return true;
                    }
                }
            }
            new HelpCommand(player);
        }
        return true;
    }

    @EventHandler
    public void consumeItem(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if(item.getType().equals(Material.POTION)) {
            for(String s : getPotionList()) {
                if(item.getItemMeta().getLore().get(0).equalsIgnoreCase("iPot: " + s)) {
                    if(!player.hasPermission("ipot.consume")) {
                        Lib.msg(player, "&cError: you don't have permission to consume this item");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }


    @EventHandler
    public void throwItem(ProjectileLaunchEvent event) {
        Projectile proj = event.getEntity();
        if(proj.getShooter() instanceof Player) {
            Player player = (Player)event.getEntity().getShooter();
            if(proj.getType() == EntityType.SPLASH_POTION) {
                ThrownPotion pot = (ThrownPotion) proj;
                for(String s : getPotionList()) {
                    if(pot.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("iPot: " + s)) {
                        if(!player.hasPermission("ipot.throw")) {
                            Lib.msg(player, "&cError: you don't have permission to throw this item");
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }

    }

    @EventHandler
    public void onGUIClosed(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        if(event.getView().getTitle().equalsIgnoreCase("Set Potion Effects")) {
            try {
                Material mat = event.getInventory().getItem(0).getType();
                HashSet<String> effects = new HashSet<>();
                for(ItemStack item : event.getInventory().getContents()) {
                    if(item == null) {
                        continue;
                    }
                    if(item.getType().equals(Material.POTION) || !item.getType().equals(Material.SPLASH_POTION) || !item.getType().equals(Material.LINGERING_POTION)) {
                        PotionMeta pmeta = (PotionMeta) item.getItemMeta();
                        effects.add(pmeta.getBasePotionData().getType().toString());
                    }
                }
                PotionCreatorGUI gui = PotionCreator.pendingPotions.get(player.getUniqueId());
                String name = gui.name;
                String display = gui.display;
                String color = gui.color;
                String type = "base";
                if(mat.equals(Material.SPLASH_POTION)) {
                    type = "splash";
                }
                if(mat.equals(Material.LINGERING_POTION)) {
                    type = "area";
                }
                getConfig().set("potions."+name+".display", display);
                getConfig().set("potions."+name+".type", type);
                getConfig().set("potions."+name+".color", color);
                for(String s : effects) {
                    getConfig().set("potions."+name+".effects."+s+".duration", 1);
                    getConfig().set("potions."+name+".effects."+s+".strength", 1);
                }
                saveConfig();
                Lib.msg(player, "&aSuccessful Creation: &6<"+name+">&a was created" );


            } catch (NullPointerException e) {
                Lib.msg(player, "&cCanceled Creation: no potions added");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public ArrayList<String> getPotionList() {

        try {
            ArrayList<String> list = new ArrayList<>();
            Set<String> pots = getConfig().getConfigurationSection("potions").getKeys(false);
            for(String s : pots) {
                list.add(s.toLowerCase());
            }
            return list;
        } catch (Exception e) {
            return null;
        }

    }
}
