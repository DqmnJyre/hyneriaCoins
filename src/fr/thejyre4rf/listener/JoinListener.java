package fr.thejyre4rf.listener;

import fr.thejyre4rf.api.Coins;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        System.out.println("join");
        Player p = e.getPlayer();
        Coins c = new Coins(p);
        if(!c.exist()){
            c.set(50);
        }
    }


}
