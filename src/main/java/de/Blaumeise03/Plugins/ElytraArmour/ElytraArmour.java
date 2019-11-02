
/*
 * Copyright (c) 2019 Blaumeise03
 */

package de.Blaumeise03.Plugins.ElytraArmour;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ElytraArmour extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        getLogger().info("Author: Blaumeise03");
        getLogger().info("Starting ElytraArmour...");
        plugin = this;
        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        //getServer().getPluginManager().registerEvents(new JoinListener(), this);
        //getServer().getPluginManager().registerEvents(new ElytraArmour(), this);
        getLogger().info("ElytraArmour enabled! Have Fun with it!");
    }

    @Override
    public void onDisable() {

    }


}
