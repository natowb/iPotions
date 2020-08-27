package me.natowb.ipotions.commands;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.gui.TypeSelectorGUI;
import me.natowb.ipotions.iPotions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;


            if(args.length>1) {
                if(args[0].equalsIgnoreCase("create")) {
                    new CreateCommand(player, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("edit")) {
                    new EditCommand(player, args);
                    return true;
                }
            }

            if(args.length>0) {
                if(args[0].equalsIgnoreCase("help")) {
                    new HelpCommand(player);
                    return true;

                }

                if(args.length == 2) {
                    if(args[0].equalsIgnoreCase("debug")) {
                        String name = args[1];
                        new TypeSelectorGUI(player, name);
                        return true;
                    }
                }

                if(args[0].equalsIgnoreCase("gone")) {
                    String hello = Lib.convertToInvisibleString("Hello World");
                    player.sendMessage(hello);
                    player.sendMessage(Lib.convertToVisibleString(hello));
                }

                if(args.length==2) {
                    if(args[0].equalsIgnoreCase("delete")) {
                        new DeleteCommand(player, args);
                        return true;
                    }
                }
                if(args.length>=3) {
                    //give <player> <potion> [amount]
                    if(args[0].equalsIgnoreCase("give")) {
                        new GiveCommand(player, args);
                        return true;
                    }
                    //create <name> <display> <color>

                    //edit <name> <attribute> <id> <value>

                }
            }
            new HelpCommand(player);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1) {
            return Arrays.asList("create", "edit", "delete", "give", "help");
        }
        if(args.length == 2) {
            String t = args[0];
            if (t.equalsIgnoreCase("edit") || t.equalsIgnoreCase("delete")) {
                return PotionManager.getPotionList();
            }
            if(t.equalsIgnoreCase("create")) {
                return Arrays.asList("<Potion Name>");
            }
            if (t.equalsIgnoreCase("give")) {
                ArrayList<String> players = new ArrayList<>();
                for(Player player : Bukkit.getOnlinePlayers()) {
                    players.add(player.getName());
                }
                return players;
            }
        }
        if(args.length == 3) {
            if(args[0].equalsIgnoreCase("give")) {
                return PotionManager.getPotionList();
            }
        }

        return null;
    }
}
