package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import org.bukkit.entity.Player;

public class BaseCommand {

    public static boolean checkPerms(Player player, String perm) {
        if(!player.hasPermission("ipot."+perm)) {
            Lib.msg(player, "&cError: you don't have access to this command");
            return false;
        }
        return true;
    }

}
