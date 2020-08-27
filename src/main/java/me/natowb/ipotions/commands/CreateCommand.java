package me.natowb.ipotions.commands;
import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.gui.TypeSelectorGUI;
import org.bukkit.entity.Player;

public class CreateCommand {
    private Player player;
    private String[] args;

    public CreateCommand(Player player, String[] args) {
        this.player = player;
        this.args = args;

        run();
    }




    public void run() {
        if(!BaseCommand.checkPerms(player, "create")) return;

        String name = "";
        for(int i = 1; i < args.length; i++) {
            name+=args[i]+" ";
        }
        String check = name.replaceAll("&.", "");
        check = check.replaceAll("\\s+", "");
        if(PotionManager.getPotionList().contains(check.toLowerCase())) {
            Lib.msg(player, "&cError: &6<" + check + "> &cPotion with that name exists");
            return;
        }
        new TypeSelectorGUI(player, name.trim());
    }

}














