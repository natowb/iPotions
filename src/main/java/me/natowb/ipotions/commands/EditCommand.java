package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.Main;
import me.natowb.ipotions.creator.PotionCreator;
import me.natowb.ipotions.creator.PotionCreatorGUI;
import org.bukkit.ChatColor;
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


        String name = args[1];
        String attribute = args[2];
        String value = args[3];

        if(!Main.ins.getPotionList().contains(name.toLowerCase())) {
            Lib.msg(player, "&cError: &6<" + args[1] + "> &cnot a valid iPot potion");
            return ;
        }

        if(attribute.equalsIgnoreCase("type")) {
            editType(name, value);
        }
        if(attribute.equalsIgnoreCase("display")) {
            editDisplay(name, value);
        }
        if(attribute.equalsIgnoreCase("color")) {
            editColor(name, value);
        }
        if(attribute.equalsIgnoreCase("effect")) {

            if(args.length == 5) {
                if(args[4].equalsIgnoreCase("delete")) {
                    if(Main.ins.getConfig().get("potions."+name+".effects."+value.toLowerCase())!=null) {
                        Main.ins.getConfig().set("potions."+name+".effects."+value.toLowerCase(), null);
                        Main.ins.saveConfig();
                        Lib.msg(player, "&cRemoved effect &6<"+value+"> &cfrom "+name);
                        return;
                    } else{
                        Lib.msg(player, "&cError effect &6<"+value+"> &cnot on "+name);
                        return;
                    }
                }
            }


            if(args.length!=6) {
                Lib.msg(player, "&cError: /ipot <name> <effect> <duration> <strength>d");
                return;
            }

            int duration = 0;
            try {duration = Integer.parseInt(args[4]);} catch (NumberFormatException e)
            { Lib.msg(player, "&cError: &6<" + args[4] + "> &cnot a valid number"); return ;};

            int strength = 0;
            try {strength = Integer.parseInt(args[5]);} catch (NumberFormatException e)
            { Lib.msg(player, "&cError: &6<" + args[5] + "> &cnot a valid number"); return ;};
            //edit demo potion d 100
            editEffects(name, value.toLowerCase(), duration, strength);
        }





    }
    //edit Demo type area
    public void editType(String name, String newType) {
        if(newType.equalsIgnoreCase("base") || newType.equalsIgnoreCase("splash") || newType.equalsIgnoreCase("area")) {
            Main.ins.getConfig().set("potions."+name+".type", newType);
            Main.ins.saveConfig();
            Lib.msg(player, "&aSuccessfully changed "+name+"'s type to &6<" + newType + "&6>");
            return;
        }
        Lib.msg(player, "&cError: &6<" + newType + "> &cnot a valid type");

    }
    public void editDisplay(String name, String newDisplay) {
        Main.ins.getConfig().set("potions."+name+".display", newDisplay);
        Main.ins.saveConfig();
        Lib.msg(player, "&aSuccessfully changed "+name+"'s display to &6<" + ChatColor.translateAlternateColorCodes('&', newDisplay) + "&6>");


    }
    public void editColor(String name, String newColor) {
        if(!PotionCreator.COLORS.containsKey(newColor)) {
            Lib.msg(player, "&cError: &6<" + newColor + "> &cnot a valid color");
            return;
        }
        Main.ins.getConfig().set("potions."+name+".color", newColor);
        Main.ins.saveConfig();
        Lib.msg(player, "&aSuccessfully changed "+name+"'s color to &6<" + newColor + "&6>");


    }
    //edit Demo effect poison d 1203
    public void editEffects(String name, String effect, int duration, int strength) {

        if(!PotionCreator.EFFECTS.contains(effect.toUpperCase())) {
            Lib.msg(player, "&cError: &6<" + effect + "> &cnot a valid effect");
            return;
        }

        if(Main.ins.getConfig().get("potions."+name+".effects."+effect)==null) {
            Main.ins.getConfig().set("potions."+name+".effects."+effect+".duration", duration);
            Main.ins.getConfig().set("potions."+name+".effects."+effect+".strength", strength);
            Lib.msg(player, "&aAdded  effect &6<" +  effect + " ["+String.valueOf(duration)+"]" + " ["+String.valueOf(duration)+"]" + ">&a to " +name);
        }
        else {
            Main.ins.getConfig().set("potions."+name+".effects."+effect+".duration", duration);
            Main.ins.getConfig().set("potions."+name+".effects."+effect+".strength", strength);
            Lib.msg(player, "&aUpdated  effect &6<" +  effect + " ["+String.valueOf(duration)+"]" + " ["+String.valueOf(duration)+"]" + ">&a on " +name);
        }
        Main.ins.saveConfig();
    }


}














