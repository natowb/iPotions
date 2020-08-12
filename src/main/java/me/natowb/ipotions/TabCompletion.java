package me.natowb.ipotions;

import me.natowb.ipotions.creator.PotionCreator;
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

    if (args.length == 1) {
        ArrayList<String> commands = new ArrayList(Arrays.asList("give", "help", "create", "delete", "edit"));
        return commands;
    }
    if (args.length == 2) {
        if (args[0].equalsIgnoreCase("give")) {
            ArrayList<String> playerNames = new ArrayList<>();
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                playerNames.add(p.getName());
            }
            return playerNames;
        }
        if (args[0].equalsIgnoreCase("create")) {
            return Arrays.asList("<name>");
        }
        if (args[0].equalsIgnoreCase("delete")) {
            return Main.ins.getPotionList();
        }
        if (args[0].equalsIgnoreCase("edit")) {
            return Main.ins.getPotionList();

        }
    }

        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("give")) {
                return Main.ins.getPotionList();
            }
            if (args[0].equalsIgnoreCase("create")) {
                return Arrays.asList("<display>");
            }
            if (args[0].equalsIgnoreCase("edit")) {
                return Arrays.asList("type", "display", "color", "effect");
            }
        }

        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("give")) {
                return Arrays.asList("1");
            }
            if (args[0].equalsIgnoreCase("create")) {
                return new ArrayList<>(PotionCreator.COLORS.keySet());
            }
            if (args[0].equalsIgnoreCase("edit")) {
                if (args[2].equalsIgnoreCase("type")) {
                    return Arrays.asList("base", "splash", "area");
                }
                if (args[2].equalsIgnoreCase("display")) {
                    return Arrays.asList("<displayName>");
                }
                if (args[2].equalsIgnoreCase("color")) {
                    return new ArrayList<>(PotionCreator.COLORS.keySet());
                }
                if (args[2].equalsIgnoreCase("effect")) {
                    return new ArrayList<>(PotionCreator.EFFECTS);
                }
            }

        }

        if (args.length == 5) {
            if (args[0].equalsIgnoreCase("edit")) {
                if (args[2].equalsIgnoreCase("effect")) {
                    return Arrays.asList("delete","<duration>");
                }
            }
        }

        if (args.length == 6) {
            if (args[0].equalsIgnoreCase("edit")) {
                if (args[2].equalsIgnoreCase("effect")) {
                    return Arrays.asList("<strength>");
                }
            }
        }

        return null;
    }
}
