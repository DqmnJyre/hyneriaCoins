package fr.thejyre4rf.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisSimpleCache {

    private final String cacheIp;
    private String password = null;
    private final Integer cachePort;
    private final Integer dataBase;
    protected JedisPool cachePool;

    public RedisSimpleCache(String cacheIp, Integer cachePort, String password, Integer dataBase) {
        this.cacheIp = cacheIp;
        this.cachePort = cachePort;
        this.password = password;
        this.dataBase = dataBase;
        try {
            initiateConnections();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public RedisSimpleCache(String cacheIp, Integer cachePort, Integer dataBase) {
        this.cacheIp = cacheIp;
        this.cachePort = cachePort;
        this.dataBase = dataBase;
        try {
            initiateConnections();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Jedis getCacheResource() {
        return cachePool.getResource();
    }

    public void killConnections() {
        cachePool.destroy();
    }

    public void initiateConnections() throws InterruptedException {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(1024);
        config.setMaxWaitMillis(5000);
        if(password != null) {
            this.cachePool = new JedisPool(config, cacheIp, cachePort, 5000, password, dataBase);
        }
        else{
            this.cachePool = new JedisPool(config, cacheIp, cachePort, 5000);
        }
    }

    public void disable() {
        killConnections();
    }

}
