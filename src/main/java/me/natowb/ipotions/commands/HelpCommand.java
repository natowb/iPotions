package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.PotionMeta;

public class HelpCommand {

    private Player player;

    public HelpCommand(Player player) {

        if(player.getInventory().getItemInMainHand()!=null) {
            if(player.getInventory().getItemInMainHand().getType() == Material.POTION) {
                Lib.msg(player, ((PotionMeta)player.getInventory().getItemInMainHand().getItemMeta()).getCustomEffects().toString());
            }
        }

        if(!BaseCommand.checkPerms(player, "help")) return;
        this.player = player;
        run();
    }



    public void run() {
        String help = "" +
                "&e###########  &6iPotions Help  &e###########&r\n" +
                "&6/ipot create &b<potion name>\n" +
                "&6/ipot delete &b<potion name>\n" +
                "&6/ipot edit &b<potion name>\n" +
                "&6/ipot give &c<player> &b<potion name> &a[amount]&r\n" +
                "&6/ipot give &c<player> &b<potion name> &a[amount]&r\n";
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
    }


}
