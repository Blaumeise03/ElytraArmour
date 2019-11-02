/*
 * Copyright (c) 2019 Blaumeise03
 */

package de.Blaumeise03.Plugins.ElytraArmour;

import net.minecraft.server.v1_14_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CraftingListener implements Listener {


    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        //Bukkit.broadcastMessage("Click");
        try {
            if (e.getCurrentItem().getType() == Material.ELYTRA)
                if (e.getCurrentItem().getItemMeta().getLore().contains("§4Kostet §a30 Level§4!")) {
                    ((Player) e.getWhoClicked()).setLevel(((Player) e.getWhoClicked()).getLevel() - 30);
                    ItemStack stack = e.getCurrentItem();
                    ItemMeta meta = stack.getItemMeta();
                    meta.setLore(Arrays.asList("", "§6Verbesserte Elytra"));
                    stack.setItemMeta(meta);
                    e.setCurrentItem(stack);
                    e.getWhoClicked().sendMessage("§aElytra verbessert! Viel Spaß damit!");
                    ((Player) e.getWhoClicked()).updateInventory();
                    return;
                }
        } catch (NullPointerException ignored) {
        }
        if (e.getInventory().getType() == InventoryType.WORKBENCH) {
            //Bukkit.broadcastMessage("Work");
            //Bukkit.broadcastMessage(String.valueOf(e.getInventory().getContents().length));
            boolean recCorrect = true;
            boolean ely = false;
            boolean chest = false;
            for (int i = 1; i <= 9; i++) {
                ItemStack stack = e.getInventory().getItem(i);
                if (stack != null) {
                    //Bukkit.broadcastMessage(i + " " + stack.getType());
                    if (!ely && stack.getType() == Material.ELYTRA) ely = true;
                    else if (!chest && stack.getType() == Material.DIAMOND_CHESTPLATE
                            || stack.getType() == Material.IRON_CHESTPLATE
                            || stack.getType() == Material.GOLDEN_CHESTPLATE
                            || stack.getType() == Material.LEATHER_CHESTPLATE
                            || stack.getType() == Material.CHAINMAIL_CHESTPLATE) chest = true;
                    else recCorrect = false;
                }


            }
            final HumanEntity player = e.getWhoClicked();
            Bukkit.getScheduler().runTaskLater(ElytraArmour.plugin, new Runnable() {
                public void run() {
                    //Bukkit.broadcastMessage("T");
                    if (!(player.getOpenInventory().getType() == InventoryType.WORKBENCH)) return;
                    boolean recCorrect = true;
                    boolean ely = false;
                    boolean chest = false;
                    //Bukkit.broadcastMessage("t");
                    for (int i = 1; i <= 9; i++) {
                        ItemStack stack = player.getOpenInventory().getItem(i);
                        if (stack != null) if (stack.getType() != Material.AIR) {
                            //Bukkit.broadcastMessage(i + " " + stack.getType());
                            if (!ely && stack.getType() == Material.ELYTRA) ely = true;
                            else if (!chest && (stack.getType() == Material.DIAMOND_CHESTPLATE
                                    || stack.getType() == Material.IRON_CHESTPLATE
                                    || stack.getType() == Material.GOLDEN_CHESTPLATE
                                    || stack.getType() == Material.LEATHER_CHESTPLATE
                                    || stack.getType() == Material.CHAINMAIL_CHESTPLATE)) chest = true;
                            else recCorrect = false;
                        }
                    }
                    //Bukkit.broadcastMessage("cor " + recCorrect + " el " + ely + " ch " + chest);
                    if (recCorrect && ely && chest) {
                        //Bukkit.broadcastMessage("Correct");
                        if (((Player) player).getLevel() >= 30) {
                            //Bukkit.broadcastMessage("30 Lv");
                            ItemStack resElytra = new ItemStack(Material.ELYTRA);
                            ItemStack elytra = null;
                            ItemStack plate = null;
                            try {
                                for (int i = 1; i <= 9; i++) {
                                    if (player.getOpenInventory().getItem(i) != null)
                                        if (player.getOpenInventory().getItem(i).getType() == Material.ELYTRA) {
                                            elytra = player.getOpenInventory().getItem(i);
                                        }
                                }

                                for (int i = 1; i <= 9; i++) {
                                    if (player.getOpenInventory().getItem(i) != null)
                                        if (player.getOpenInventory().getItem(i).getType() == Material.DIAMOND_CHESTPLATE
                                                || player.getOpenInventory().getItem(i).getType() == Material.IRON_CHESTPLATE
                                                || player.getOpenInventory().getItem(i).getType() == Material.GOLDEN_CHESTPLATE
                                                || player.getOpenInventory().getItem(i).getType() == Material.LEATHER_CHESTPLATE
                                                || player.getOpenInventory().getItem(i).getType() == Material.CHAINMAIL_CHESTPLATE) {
                                            plate = player.getOpenInventory().getItem(i);
                                        }
                                }
                            } catch (NullPointerException ignored) {
                                ignored.printStackTrace();
                                return;
                            }
                            //ItemMeta elytraMeta = elytra.getItemMeta();
                            //Bukkit.broadcastMessage("Completing");
                            Map<Enchantment, Integer> elytraEnchantments = elytra.getEnchantments();
                            Map<Enchantment, Integer> plateEnchantments = plate.getEnchantments();
                            Map<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();

                            for (Enchantment e : elytraEnchantments.keySet()) {
                                if (plateEnchantments.containsKey(e)) {
                                    //Bukkit.broadcastMessage(plateEnchantments.get(e) + " | " + elytraEnchantments.get(e));
                                    if (plateEnchantments.get(e) < elytraEnchantments.get(e)) {
                                        enchantments.put(e, elytraEnchantments.get(e));
                                    } else enchantments.put(e, plateEnchantments.get(e));
                                } else enchantments.put(e, elytraEnchantments.get(e));
                            }
                            for (Enchantment e : plateEnchantments.keySet()) {
                                if (!enchantments.containsKey(e)) {
                                    enchantments.put(e, plateEnchantments.get(e));
                                    //Bukkit.broadcastMessage(e.toString() + " :" + plateEnchantments.get(e));
                                }
                            }

                            //enchantments.putAll(elytraEnchantments);
                            resElytra.addUnsafeEnchantments(enchantments);
                            //resElytra.addUnsafeEnchantments(plate.getEnchantments());

                            //NMS Stuff
                            net.minecraft.server.v1_14_R1.ItemStack item = CraftItemStack.asNMSCopy(resElytra);
                            NBTTagCompound c = item.getTag();
                            if (c == null) {
                                c = new NBTTagCompound();
                                item.setTag(c);
                                c = item.getTag();
                            }
                            NBTTagList modifiers = c.getList("AttributeModifiers", 10);
                            NBTTagCompound armor = new NBTTagCompound();
                            double armorC = 0;
                            for (int i = 0; i < modifiers.size(); i++) {
                                NBTTagCompound c1 = modifiers.getCompound(i);
                                if (c1.getString("AttributeName").equalsIgnoreCase("generic.armor")) {
                                    armorC = c1.getDouble("Amount");
                                }
                            }
                            double armorPlate = 0;
                            double armorT = 0;
                            switch (plate.getType()) {
                                case LEATHER_CHESTPLATE:
                                    armorPlate = 3;
                                    break;
                                case GOLDEN_CHESTPLATE:
                                    armorPlate = 5;
                                    break;
                                case CHAINMAIL_CHESTPLATE:
                                    armorPlate = 5;
                                    break;
                                case IRON_CHESTPLATE:
                                    armorPlate = 6;
                                    break;
                                case DIAMOND_CHESTPLATE:
                                    armorPlate = 8;
                                    armorT = 2;
                                    break;
                                default:
                                    armorPlate = 0;
                            }
                            if (armorC > armorPlate) armorPlate = armorC;
                            armor.set("AttributeName", new NBTTagString("generic.armor"));
                            armor.set("Name", new NBTTagString("generic.armor"));
                            armor.set("Amount", new NBTTagDouble(armorPlate));
                            armor.set("Operation", new NBTTagInt(0));
                            armor.set("UUIDLeast", new NBTTagInt(649074));
                            armor.set("UUIDMost", new NBTTagInt(798746));
                            armor.set("Slot", new NBTTagString("chest"));
                            modifiers.add(armor);
                            if (armorT != 0) {
                                NBTTagCompound armourTough = new NBTTagCompound();
                                armourTough.set("AttributeName", new NBTTagString("generic.armorToughness"));
                                armourTough.set("Name", new NBTTagString("generic.armorToughness"));
                                armourTough.set("Amount", new NBTTagDouble(armorT));
                                armourTough.set("Operation", new NBTTagInt(0));
                                armourTough.set("UUIDLeast", new NBTTagInt(649074));
                                armourTough.set("UUIDMost", new NBTTagInt(798746));
                                armourTough.set("Slot", new NBTTagString("chest"));
                                modifiers.add(armourTough);
                            }
                            c.set("AttributeModifiers", modifiers);
                            item.setTag(c);
                            resElytra = CraftItemStack.asBukkitCopy(item);
                            ItemMeta meta = resElytra.getItemMeta();
                            meta.setLore(Collections.singletonList("§4Kostet §a30 Level§4!"));
                            resElytra.setItemMeta(meta);
                            ((CraftingInventory) player.getOpenInventory().getTopInventory()).setResult(resElytra);
                            ((Player) player).updateInventory();

                        } else {
                            player.sendMessage("§4Du benötigst 30 Level um dies zu zun!");
                            ((CraftingInventory)player.getOpenInventory().getTopInventory()).setResult(null);
                            //player.closeInventory();
                        }
                    }//else Bukkit.broadcastMessage("Incorrect");
                }
            }, 2);


        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        //Bukkit.broadcastMessage("T");
        try {
            if (e.getRecipe().getResult().hasItemMeta())
                if (e.getRecipe().getResult().getItemMeta().hasLore())
                    if (e.getRecipe().getResult().getItemMeta().getLore().contains("§4Kostet §a30 Level§4!")) {
                        ((Player) e.getWhoClicked()).setLevel(((Player) e.getWhoClicked()).getLevel() - 30);
                        ItemStack stack = e.getRecipe().getResult();
                        ItemMeta meta = stack.getItemMeta();
                        meta.setLore(Arrays.asList("", "§4Verbesserte Elytra"));
                        stack.setItemMeta(meta);
                        e.setCurrentItem(stack);
                        e.getWhoClicked().sendMessage("§aElytra verbessert! Viel Spaß damit!");
                    }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
