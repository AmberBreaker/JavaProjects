package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisStringTest {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /*通过java程序访问redis*/
    @Test
    public void setAndGetFunction() {
        Jedis jedis = new Jedis("47.105.108.215", 6379);
        jedis.set("username", "zhangsan");
        System.out.println(jedis.get("username"));
        jedis.set("username", "lisi");
        System.out.println(jedis.get("username"));
    }

    /*通过jedis的pool获得连接对象*/
    @Test
    public void jedisPool() {
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

    /*删除*/
    @Test
    public void delete() {
        jedis.del("username");
        System.out.println(jedis.get("username"));
    }

    /*数值增减*/
    @Test
    public void numbicModify() {
        jedis.del("age");
        // 自增(increase),若查出该key为nil，则默认为0
        jedis.incr("age");
        System.out.println(jedis.get("age"));
        // 自减(decrease)
        jedis.decr("age");
        System.out.println(jedis.get("age"));
        // 增加指定数值
        jedis.incrBy("age", 10);
        System.out.println(jedis.get("age"));
        jedis.decrBy("age", 10);
        System.out.println(jedis.get("age"));
    }

    /*拼接字符串*/
    @Test
    public void appendStr() {
        jedis.set("username", "zhangsan");
        jedis.append("username", "123");
        System.out.println(jedis.get("username"));
        jedis.set("age", "1");
        jedis.append("age", "2");
        System.out.println(jedis.get("age"));
        Long username = jedis.del("username");
        System.out.println(username);
    }
}
