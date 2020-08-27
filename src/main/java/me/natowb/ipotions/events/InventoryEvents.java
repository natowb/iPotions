package me.natowb.ipotions.events;

import me.natowb.ipotions.Lib;
import me.natowb.ipotions.creator.PotionManager;
import me.natowb.ipotions.gui.ColorSelectorGUI;
import me.natowb.ipotions.gui.EffectTunerGUI;
import me.natowb.ipotions.gui.InfoGUI;
import me.natowb.ipotions.iPotions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class InventoryEvents implements Listener {



    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Main Reports Menu
        Player player = (Player)event.getView().getPlayer();
        if(event.getClickedInventory()==null) {
            return;
        }
        if (event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
            if(event.getCurrentItem() != null) {
                ItemStack item = event.getCurrentItem();
                Material type = item.getType();
                String strippedName = ChatColor.stripColor(item.getItemMeta().getDisplayName());
                String name = item.getItemMeta().getDisplayName();
                String title = event.getView().getTitle();

                if(title.equalsIgnoreCase("iPotions: Select Type")) {



                    if(type == Material.POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            Lib.msg(player, "editing");
                            iPotions.ins.potionManager.editPotionType(player, strippedName.toLowerCase(), "base");
                        }
                        new ColorSelectorGUI(player, name, "base");
                    }
                    if(type == Material.SPLASH_POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            Lib.msg(player, "editing");
                            iPotions.ins.potionManager.editPotionType(player, strippedName.toLowerCase(), "splash");
                        }
                        new ColorSelectorGUI(player, name, "splash");

                    }
                    if(type == Material.LINGERING_POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            Lib.msg(player, "editing");
                            iPotions.ins.potionManager.editPotionType(player, strippedName.toLowerCase(), "area");
                        }
                        new ColorSelectorGUI(player, name, "area");
                    }


                    event.setCancelled(true);

                }

                if(title.equalsIgnoreCase("iPotions: Select Color")) {

                    PotionMeta meta = (PotionMeta) item.getItemMeta();

                    if(type == Material.POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            iPotions.ins.potionManager.editPotionColor(player, strippedName.toLowerCase(),meta.getColor());
                            new InfoGUI(player, PotionManager.getPotion(strippedName.toLowerCase(),1));
                        } else {
                            new InfoGUI(player, item);
                        }
                    }
                    if(type == Material.SPLASH_POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            iPotions.ins.potionManager.editPotionColor(player, strippedName.toLowerCase(),meta.getColor());
                            new InfoGUI(player, PotionManager.getPotion(strippedName.toLowerCase(),1));
                        } else {
                            new InfoGUI(player, item);
                        }
                    }
                    if(type == Material.LINGERING_POTION) {
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            iPotions.ins.potionManager.editPotionColor(player, strippedName.toLowerCase(),meta.getColor());
                            new InfoGUI(player, PotionManager.getPotion(strippedName.toLowerCase(),1));
                        } else {
                            new InfoGUI(player, item);
                        }
                    }


                    event.setCancelled(true);
                }

                if(title.equalsIgnoreCase("iPotions: Manage Effects")) {


                    ItemStack potion = event.getClickedInventory().getItem(4);
                    PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();


                    if(event.getSlot()==4) {
                        boolean isEdit = false;
                        if(PotionManager.getPotionList().contains(strippedName.toLowerCase())) {
                            isEdit = true;
                        }
                        if(iPotions.ins.potionManager.createPotion(player, potion)) {
                            if(isEdit) {
                                Lib.msg(player, "&bCompleted editing &6<"+strippedName+"&6>");
                            } else {
                                Lib.msg(player, "&bCreated potion &6<"+strippedName+"&6>");
                            }
                        }
                        player.closeInventory();
                        event.setCancelled(true);
                        return;
                    }


                    if(type == Material.POTION) {
                        PotionMeta clickedMeta = (PotionMeta) item.getItemMeta();
                        if(item.getItemMeta().getLore().get(0).contains("Not")) {
                            player.closeInventory();
                            new EffectTunerGUI(player, potion,  item);
                            return;
                        } else {
                            for(PotionEffectType effect : Lib.EFFECTS.get(clickedMeta.getBasePotionData().getType())) {
                                potionMeta.removeCustomEffect(effect);
                            }
                        }
                        potion.setItemMeta(potionMeta);
                        new InfoGUI(player, potion);
                    }
                    if(type == Material.SPLASH_POTION) {
                        PotionMeta clickedMeta = (PotionMeta) item.getItemMeta();
                        if(item.getItemMeta().getLore().get(0).contains("Not")) {
                            player.closeInventory();
                            new EffectTunerGUI(player, potion,  item);
                            return;
                        } else {
                            for(PotionEffectType effect : Lib.EFFECTS.get(clickedMeta.getBasePotionData().getType())) {
                                potionMeta.removeCustomEffect(effect);
                            }
                        }
                        potion.setItemMeta(potionMeta);
                        new InfoGUI(player, potion);
                    }
                    if(type == Material.LINGERING_POTION) {
                        PotionMeta clickedMeta = (PotionMeta) item.getItemMeta();
                        if(item.getItemMeta().getLore().get(0).contains("Not")) {
                            player.closeInventory();
                            new EffectTunerGUI(player, potion,  item);
                            return;
                        } else {
                            for(PotionEffectType effect : Lib.EFFECTS.get(clickedMeta.getBasePotionData().getType())) {
                                potionMeta.removeCustomEffect(effect);
                            }
                        }
                        potion.setItemMeta(potionMeta);
                        new InfoGUI(player, potion);
                    }
                    event.setCancelled(true);
                }

                if(title.equalsIgnoreCase("iPotions: Tune Effect")) {
                    if(event.getSlot() == 4) {
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(meta.getLore().get(0));
                        item.setItemMeta(meta);
                        event.setCancelled(true);
                        player.closeInventory();
                        new InfoGUI(player, item);
                        return;

                    }
                    if(type == Material.POTION) {
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        PotionMeta potionMeta = (PotionMeta) event.getClickedInventory().getItem(4).getItemMeta();
                        potionMeta.setDisplayName(meta.getLore().get(0));
                        for(PotionEffect effect : meta.getCustomEffects()) {
                                potionMeta.addCustomEffect(effect, true);
                        }
                        event.getClickedInventory().getItem(4).setItemMeta(potionMeta);
                        new InfoGUI(player, event.getClickedInventory().getItem(4));
                        event.setCancelled(true);
                    }

                    if(strippedName.equalsIgnoreCase("Duration: +5")) {
                        PotionMeta meta = (PotionMeta) event.getClickedInventory().getItem(22).getItemMeta();
                        PotionEffect ef = meta.getCustomEffects().get(0);
                        int duration = ef.getDuration()+(5*20);
                        int strength = ef.getAmplifier();
                        meta.addCustomEffect(new PotionEffect(ef.getType(), duration, strength), true);
                        event.getClickedInventory().getItem(22).setItemMeta(meta);
                        event.setCancelled(true);
                    }
                    if(strippedName.equalsIgnoreCase("Duration: -5")) {
                        PotionMeta meta = (PotionMeta) event.getClickedInventory().getItem(22).getItemMeta();
                        PotionEffect ef = meta.getCustomEffects().get(0);
                        int duration = ef.getDuration()-(5*20);
                        int strength = ef.getAmplifier();
                        meta.addCustomEffect(new PotionEffect(ef.getType(), duration, strength), true);
                        event.getClickedInventory().getItem(22).setItemMeta(meta);
                        event.setCancelled(true);
                    }
                    if(strippedName.equalsIgnoreCase("Strength: -1")) {
                        PotionMeta meta = (PotionMeta) event.getClickedInventory().getItem(22).getItemMeta();
                        PotionEffect ef = meta.getCustomEffects().get(0);
                        int duration = ef.getDuration();
                        int strength = ef.getAmplifier()-1;
                        meta.addCustomEffect(new PotionEffect(ef.getType(), duration, strength), true);
                        event.getClickedInventory().getItem(22).setItemMeta(meta);
                        event.setCancelled(true);
                    }
                    if(strippedName.equalsIgnoreCase("Strength: +1")) {
                        PotionMeta meta = (PotionMeta) event.getClickedInventory().getItem(22).getItemMeta();
                        PotionEffect ef = meta.getCustomEffects().get(0);
                        int duration = ef.getDuration();
                        int strength = ef.getAmplifier()+1;
                        meta.addCustomEffect(new PotionEffect(ef.getType(), duration, strength), true);
                        event.getClickedInventory().getItem(22).setItemMeta(meta);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
