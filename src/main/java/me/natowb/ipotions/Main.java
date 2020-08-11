package me.natowb.ipotions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Set;

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

            if(args.length==1) {
                if(args[1].equalsIgnoreCase("help")) {
                    if(!player.hasPermission("ipot.help")) {
                        Lib.msg(player, "&cError: you don't have access to this command");
                        return true;
                    }
                    String help = "" +
                            "&e###########  &6iPotions Help  &e###########&r\n" +
                            "&6/ipot give &c<player> &c<potion> &a[amount]&r";

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                    return true;
                }
            }

            if(args.length>=3) {
                if(args[0].equalsIgnoreCase("give")) {
                    if(!player.hasPermission("ipot.give")) {
                        Lib.msg(player, "&cError: you don't have access to this command");
                        return true;
                    }
                    // give <player> <potion> <amount>
                    Player target = Bukkit.getPlayer(args[1]);
                    String potion = args[2];
                    int amount = 1;
                    if(args.length == 4) {
                        try {amount = Integer.parseInt(args[3]);} catch (NumberFormatException e)
                        { Lib.msg(player, "&cError: &6<" + args[3] + "> &cnot a valid number"); return true;};
                    }
                    if(target == null) {
                        Lib.msg(player, "&cError: &6<" + args[1] + "> &cnot a valid player");
                        return true;
                    }
                    if(!PotionCreator.validPotion(potion)) {
                        Lib.msg(player, "&cError: &6<" + potion + "> &cpotion doesn't exist");
                        return true;
                    }
                    Lib.msg(player, "&aGave &6<" + target.getName() +" "+ String.valueOf(amount) +  ">&a " + potion);
                    target.getInventory().addItem(PotionCreator.createPotion(potion, amount));
                    Lib.msg(target, "&aRecieved &6<" +  String.valueOf(amount) +  " " + potion + ">&a from " + player.getName());
                    return true;
                }
            }
            if(player.hasPermission("ipot.help")) {
                String help = "" +
                        "&e###########  &6iPotions Help  &e###########&r\n" +
                        "&6/ipot give &c<player> &c<potion> &a[amount]&r";
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
                return true;
            } else {
                Lib.msg(player, "&cError: you don't have access to this command");
                return true;
            }


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


    public ArrayList<String> getPotionList() {

        try {
            Set<String> pots = getConfig().getConfigurationSection("potions").getKeys(false);
            return new ArrayList<String>(pots);
        } catch (Exception e) {
            return null;
        }

    }
}
