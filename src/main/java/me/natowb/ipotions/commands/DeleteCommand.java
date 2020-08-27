package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.iPotions;
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
        if(iPotions.ins.potionManager.deletePotion(name.toLowerCase())) {
            Lib.msg(player, "&aSuccessfully deleted &6<" + name + ">");
        } else {
            Lib.msg(player, "&cError: &6<" + name + "> &cPotion with that name doesn't exists");
        }
    }

}














