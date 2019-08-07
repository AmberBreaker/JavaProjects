package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * sorted-set
 */
public class JedisSortedSet {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    @Test
    public void zadd() {
        jedis.flushDB();
        Long zadd = jedis.zadd("mysortedset", 1, "a");
        Long zadd1 = jedis.zadd("mysortedset", 2, "b");

        jedis.flushDB();
    }
}
