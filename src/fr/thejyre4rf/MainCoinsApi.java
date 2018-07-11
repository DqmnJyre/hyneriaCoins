package fr.thejyre4rf;

import fr.thejyre4rf.commands.CoinsCommand;
import fr.thejyre4rf.listener.JoinListener;
import fr.thejyre4rf.redis.RedisSimpleCache;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCoinsApi extends JavaPlugin {

    public static MainCoinsApi instance;
    public static RedisSimpleCache redisSimpleCache;
    JedisPool pool;


    public static MainCoinsApi getInstance() {
        return instance;
    }

    public RedisSimpleCache getRedisSimpleCache() {
        return redisSimpleCache;
    }


    public void onEnable(){
        redisConfig();

        //RedisConfig
        String ip = getConfig().getString("Redis.IP");
        String password = getConfig().getString("Redis.password");
        int node = getConfig().getInt("Redis.node");
        int port = getConfig().getInt("Redis.port");

        //Jedis
        redisSimpleCache = new RedisSimpleCache(ip, port, node);

        registerCommands();
        registerEvents();
    }

    public void redisConfig() {
        getConfig().addDefault("Redis.IP", "127.0.0.1");
        getConfig().addDefault("Redis.port", 6379);
        getConfig().addDefault("Redis.node", 0);
        getConfig().addDefault("Redis.password", "password");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
    }

    public void registerCommands(){
        getCommand("coins").setExecutor(new CoinsCommand());
    }

}