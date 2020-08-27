package me.natowb.ipotions.events;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEvents implements Listener {

    @EventHandler
    public void consumeItem(PlayerItemConsumeEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if(item.getType().equals(Material.POTION)) {
            for(String s : PotionManager.getPotionList()) {
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
                for(String s : PotionManager.getPotionList()) {
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
}
