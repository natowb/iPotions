package me.natowb.ipotions;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener, CommandExecutor {


    @Override
    public void onEnable() {
        // Plugin startup logic
        setupConfig();
        getServer().getPluginManager().registerEvents(this,this);
        getCommand("ipot").setExecutor(this);
        Lib.init(this);
        PotionCreator.init(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupConfig() {
        getConfig().options().copyDefaults(true);
        getConfig().options().header(
                        "###########################################################################\n" +
                        "Created by NatoWB\n" +
                        "https://github.com/natowb\n" +
                        "###########################################################################\n" +
                        "Potions\n" +
                        "goto https://github.com/natowb/iPotion/ for potions colors & effects \n"+
                        "###########################################################################\n" +
                        "potions:\n" +
                        "   demo:\n" +
                        "       display: 'Demo'\n" +
                        "       color: 'red'\n" +
                        "       effects:\n" +
                        "           jump:\n" +
                        "               duration: 5 # in seconds\n" +
                        "               strength: 2\n" +
                        "           luck:\n" +
                        "               duration: 5 # in seconds\n" +
                        "               strength: 2\n");
        saveConfig();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length==2) {

                String potion = args[0];
                int amount = Integer.parseInt(args[1]);

                player.getInventory().addItem(PotionCreator.createPotion(potion, amount));
                return true;
            }

            if(args.length==3) {
                PotionCreator.ColorPotions(player);
            }
        }


        return false;
    }

    @EventHandler
    public void consumeItem(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if(item.getType().equals(Material.POTION)) {
            for(String s : getConfig().getConfigurationSection("potions").getKeys(false)) {
                String name = getConfig().getString("potions."+s+".display");
                if(item.getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
                }
            }
        }
    }
}
