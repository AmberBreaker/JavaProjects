package com.shrm.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedis连接池工具
 * @author lmm
 */
public class JedisPoolUtils {

    private static JedisPool jedisPool;

    static {
        InputStream in = JedisPoolUtils.class.getClassLoader().getResourceAsStream("property/redis.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(prop.getProperty("redis.maxTotal")));
        config.setMaxIdle(Integer.parseInt(prop.getProperty("redis.maxIdle")));
        config.setMinIdle(Integer.parseInt(prop.getProperty("redis.minIdle")));
        jedisPool = new JedisPool(config,(String) prop.getProperty("redis.host"),
                Integer.parseInt(prop.getProperty("redis.port")));
    }

    /*从JedisPool中获取Jedis对象*/
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
