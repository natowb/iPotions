package me.natowb.ipotions;
import me.natowb.ipotions.commands.*;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.events.InventoryEvents;
import me.natowb.ipotions.events.PlayerEvents;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class iPotions extends JavaPlugin implements Listener {

    public static iPotions ins;
    public PotionManager potionManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ins = this;
        getConfig().options().copyHeader(true);
        getConfig().options().header(Lib.configHeader());
        getConfig().options().copyDefaults(true);
        saveConfig();
        potionManager = new PotionManager(this);
        potionManager.loadPotions();
        getServer().getPluginManager().registerEvents(new InventoryEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(),this);
        CommandManager cmd = new CommandManager();
        getCommand("ipot").setExecutor(cmd);
        getCommand("ipot").setTabCompleter(cmd);
    }

    @Override
    public void onDisable() {
        potionManager.savePotions();
    }
}
