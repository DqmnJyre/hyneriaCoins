package fr.thejyre4rf.api;

import fr.thejyre4rf.redis.PlayerCache;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Coins {

    private Player p;
    private OfflinePlayer op;
    private String uuid;
    private PlayerCache pc;


    public Coins(Player p){
        this.p = p;
        this.uuid = p.getUniqueId().toString();
        this.pc = new PlayerCache();
    }

    public Coins(OfflinePlayer p){
        this.op = p;
        this.uuid = p.getUniqueId().toString();
        this.pc = new PlayerCache();
    }

    public void add(Integer coinsToAdd){ pc.addCoins(uuid, coinsToAdd); }
    public void remove(Integer coinsToRemove){ pc.removeCoins(uuid, coinsToRemove); }
    public void set(Integer coins){ pc.setCoins(uuid, coins); }
    public Integer get(){ return pc.getCoins(uuid); }
    public Boolean exist(){ return pc.exist(uuid); }

}
