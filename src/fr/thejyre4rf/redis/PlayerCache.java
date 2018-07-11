package fr.thejyre4rf.redis;

import fr.thejyre4rf.MainCoinsApi;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

public class PlayerCache {

    public static Jedis jedis = MainCoinsApi.redisSimpleCache.getCacheResource();
    public static Pipeline p = jedis.pipelined();


    // GETTERS //

    public String getUUID(String uuid){
        Response<String> name = p.hget("Users:UUID:" + uuid + ":", "UUID");
        p.sync();
        close();
        return name.get();
    }

    public Integer getCoins(String uuid){
        Response<String> name = p.hget("Users:UUID:" + uuid + ":", "coins");
        p.sync();
        close();
        return Integer.parseInt(name.get());
    }

    // SETTERS //

    public void setCoins(String uuid, int coins){
        p.hset("Users:UUID:" + uuid + ":", "coins", Integer.toString(coins));
        p.sync();
        close();
    }

    // ADDERS //

    public void addCoins(String UUID, int coinsToAdd) {
        int c = getCoins(UUID);
        int coinsNow = c + coinsToAdd;
        setCoins(UUID, coinsNow);
    }

    // REMOVERS //

    public void removeCoins(String UUID, int coinsToRemove) {
        int c = getCoins(UUID);
        int coinsNow = c - coinsToRemove;
        setCoins(UUID, coinsNow);
    }

    public boolean exist(String UUID){
        System.out.println();
        Response<Boolean> b = p.hexists("Users:UUID:" + UUID + ":", "coins");
        p.sync();
        close();
        return b.get();
    }

    // OTHER

    public void close() {
        try {
            p.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
