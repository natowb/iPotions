package me.natowb.ipotions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1) {
            ArrayList<String> commands = new ArrayList(Arrays.asList("give", "help"));
            return commands;
        }
        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("give")) {
                ArrayList<String> playerNames = new ArrayList<>();
                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                    playerNames.add(p.getName());
                }
                return playerNames;
            }
        }

        if(args.length == 3) {
            return Main.ins.getPotionList();
        }

        if(args.length == 4) {
            return Arrays.asList("1");
        }

        return null;
    }
}
