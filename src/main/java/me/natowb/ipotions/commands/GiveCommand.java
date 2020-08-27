package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GiveCommand {

    private Player player;
    private String[] args;

    public GiveCommand(Player player, String[] args) {
        this.player = player;
        this.args = args;

        run();
    }





    public void run() {

        if(!BaseCommand.checkPerms(player, "give")) return;

        Player target = Bukkit.getPlayer(args[1]);
        String potion = args[2];
        int amount = 1;
        if(args.length == 4) {
            try {amount = Integer.parseInt(args[3]);} catch (NumberFormatException e)
            { Lib.msg(player, "&cError: &6<" + args[3] + "> &cnot a valid number"); return ;};
        }
        if(target == null) {
            Lib.msg(player, "&cError: &6<" + args[1] + "> &cnot a valid player");
            return ;
        }
        if(!PotionManager.validPotion(potion)) {
            Lib.msg(player, "&cError: &6<" + potion + "> &cpotion doesn't exist");
            return ;
        }
        Lib.msg(player, "&aGave &6<" + target.getName() +" "+ String.valueOf(amount) +  ">&a " + potion);
        target.getInventory().addItem(PotionManager.getPotion(potion, amount));
        Lib.msg(target, "&aRecieved &6<" +  String.valueOf(amount) +  " " + potion + ">&a from " + player.getName());
    }

}
