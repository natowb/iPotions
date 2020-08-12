package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand {

    private Player player;

    public HelpCommand(Player player) {
        if(!BaseCommand.checkPerms(player, "help")) return;
        this.player = player;
        run();
    }



    public void run() {
        String help = "" +
                "&e###########  &6iPotions Help  &e###########&r\n" +
                "&6/ipot give &c<player> &c<potion> &a[amount]&r";
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
    }


}
