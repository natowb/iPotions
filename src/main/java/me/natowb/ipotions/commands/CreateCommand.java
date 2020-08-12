package me.natowb.ipotions.commands;
import me.natowb.ipotions.Lib;
import me.natowb.ipotions.Main;
import me.natowb.ipotions.creator.PotionCreator;
import me.natowb.ipotions.creator.PotionCreatorGUI;
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

        String name = args[1];
        String display = args[2];
        String color = args[3];

        if(Main.ins.getPotionList().contains(name.toLowerCase())) {
            Lib.msg(player, "&cError: &6<" + name + "> &cPotion with that name exists");
            return;
        }
        if(!PotionCreator.COLORS.containsKey(color)) {
            Lib.msg(player, "&cError: &6<" + color + "> &cnot a valid color");
            return;
        }

        new PotionCreatorGUI(Main.ins,player, name, display, color);
        return;
    }

}














