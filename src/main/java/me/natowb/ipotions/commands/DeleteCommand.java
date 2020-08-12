package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.Main;
import me.natowb.ipotions.creator.PotionCreator;
import me.natowb.ipotions.creator.PotionCreatorGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DeleteCommand {
    private Player player;
    private String[] args;

    public DeleteCommand(Player player, String[] args) {
        this.player = player;
        this.args = args;

        run();
    }




    public void run() {

        if(!BaseCommand.checkPerms(player, "delete")) return;

        String name = args[1];
        if(!Main.ins.getPotionList().contains(name.toLowerCase())) {
            Lib.msg(player, "&cError: &6<" + name + "> &cPotion with that name doesn't exists");
            return;
        }
        Main.ins.getConfig().set("potions."+name, null);
        Main.ins.saveConfig();
        Lib.msg(player, "&aSuccessfully deleted &6<" + name + ">");
    }

}














