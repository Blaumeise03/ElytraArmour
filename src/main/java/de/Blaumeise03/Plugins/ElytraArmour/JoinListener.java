/*package de.Blaumeise03.Plugins.ElytraArmour;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class JoinListener implements Listener {
    boolean welcomeMessageSent = false;
    List<Player> welcomeMessage = new ArrayList<Player>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!welcomeMessageSent){
            //Bukkit.broadcastMessage("§aElytraArmour ist installiert! Du kannst deine Elytren mit einer RÜstung vercraften und so eine stärkere bekommen!");
            //welcomeMessageSent = true;
        }
        if(!welcomeMessage.contains(e.getPlayer())){
            //welcomeMessage.add(e.getPlayer());
            //e.getPlayer().sendMessage("§aElytraArmour ist installiert! Plugin von §4Blaumeise03");
        }
    }
}*/
