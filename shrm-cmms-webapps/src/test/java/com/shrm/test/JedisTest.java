package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

    /**
     * 通过java程序访问redis
     */
    @Test
    public void fun01() {
        Jedis jedis = new Jedis("47.105.108.215", 6379);
        jedis.set("username","zhangsan");
        System.out.println(jedis.get("username"));
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
    }

    /**
     * 通过jedis的pool获得连接对象
     */
    @Test
    public void fun02() {
        // 0：创建池子的配置对象
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);  // 最小闲置个数
        poolConfig.setMinIdle(10);  // 最大闲置个数
        poolConfig.setMaxTotal(50); // 最大连接数

        // 创建redis连接池
        JedisPool pool = new JedisPool(poolConfig, "47.105.108.215", 6379);

        // 2：从池子中获取redis的连接资源
        Jedis jedis = pool.getResource();

        // 3：操作数据
        String username = jedis.get("username");
        System.out.println(username);

        // 4：关闭资源
        jedis.close();
        pool.close();
    }

    @Test
    public void fun03() {
        Jedis jedis = JedisPoolUtils.getJedis();
        System.out.println(jedis.get("username"));
    }
}
