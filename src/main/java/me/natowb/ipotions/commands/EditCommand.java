package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.gui.TypeSelectorGUI;
import org.bukkit.entity.Player;

public class EditCommand {
    private Player player;
    private String[] args;

    public EditCommand(Player player, String[] args) {
        this.player = player;
        this.args = args;

        run();
    }
    public void run() {

        if(!BaseCommand.checkPerms(player, "edit")) return;
        String name = args[1].toLowerCase();
        String check = name.replaceAll("&.", "");
        check = check.replaceAll("\\s+", "");
        if(PotionManager.getPotionList().contains(check.toLowerCase())) {
            Lib.msg(player, "&a Found item");
            new TypeSelectorGUI(player, check);
        }
    }
}














