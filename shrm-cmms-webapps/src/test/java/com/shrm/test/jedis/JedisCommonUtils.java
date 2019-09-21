package com.shrm.test.jedis;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * keys 通用操作
 */
public class JedisCommonUtils {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /**
     * 罗列所有的key
     */
    @Test
    public void keys() {

        jedis.set("mystr1", "str1");
        jedis.set("mystr2", "str2");
        jedis.set("mystr3", "str3");
        /*
         * keys patten
         * * 为字符串通配符，? 为单一字符通配符
         */
        Set<String> keys = jedis.keys("*"); // mystr1 mystr2 mystr3
        // del key1 key2 key3 ...
        jedis.del("mystr1", "mystr2", "mystr3");
    }

    /**
     * 判断key是否存在
     */
    @Test
    public void exists() {
        jedis.hset("myhash", "field", "value");

        /**
         * exists key
         * redis为0/1，而java为Boolean类型
         */
        Boolean myset = jedis.exists("myhash");

        jedis.del("myhash");
    }

    /**
     * 将key重命名
     */
    @Test
    public void rename() {
        jedis.zadd("myzset", 10, "zset1");
        // keys *set*
        Set<String> keys = jedis.keys("*set*");
        // rename myzset mysortedset
        jedis.rename("myzset", "mysortedset");
        jedis.del("mysortedset");
    }

    /**
     * 设置过期时间
     */
    @Test
    public void expire() {
        jedis.sadd("myset", "set1", "set2");
        /*
         * expire key seconds
         * 单位为s
         */
        jedis.expire("myset", 1);
        System.out.println(jedis.exists("myset")); // true
        try {
            Thread.sleep(2000);
            System.out.print(jedis.exists("myset")); // false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jedis.del("myset");

    }

    /**
     * 判断key是否超时
     * 1. 没有设置超时，返回：-1
     * 2. 未超时，返回剩余时间（单位:s）
     * 3. 超时或不存在，返回：-2
     */
    @Test
    public void ttl() {
        jedis.rpush("mylist", "a", "b", "c");
        System.out.println(jedis.ttl("mylist")); // -1
        jedis.expire("mylist", 5);
        try {
            Thread.sleep(2000);
            System.out.println(jedis.ttl("mylist")); // 3
            Thread.sleep(3000);
            System.out.println(jedis.ttl("mylist")); // -2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jedis.del("mylist");

    }

    /**
     * 获取指定key的数据类型
     * type key
     * string，list，set，zset，hash
     */
    @Test
    public void type() {
        jedis.set("mystr", "str");
        System.out.println(jedis.type("mystr")); // string

        jedis.lpush("mylist", "3", "2", "1");
        System.out.println(jedis.type("mylist")); // list

        jedis.sadd("myset", "a", "b");
        System.out.println(jedis.type("myset")); // set

        Map<String, Double> zsets = new HashMap<>();
        zsets.put("a", 10.0);
        zsets.put("b", 20.0);
        jedis.zadd("myzset", zsets);
        System.out.println(jedis.type("myzset")); // zset

        jedis.hset("myhash", "field", "value");
        System.out.println(jedis.type("myhash")); // hash
    }
}
