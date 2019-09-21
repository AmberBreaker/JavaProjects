package com.shrm.test.jedis;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Jedis 测试类
 *
 * @author lmm
 */
public class JedisHash {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /*hash*/
    @Test
    public void hashSet() {
        // hset key field value
        jedis.hset("member", "name", "zhangsan"); // member {name = zhangsan}
        // hget key field
        String name = jedis.hget("member", "name"); // name = zhangsan

        // hset member sex male age 19
        Map<String, String> map = new HashMap<>();
        map.put("sex", "male");
        map.put("age", "19");
        jedis.hmset("member", map);

        // hget member name age sex
        List<String> list = jedis.hmget("member", "name", "age", "sex");

        // hincrby member age 5，参数可为负
        jedis.hincrBy("member", "age", 5);

        // hgetall member
        Map<String, String> member = jedis.hgetAll("member");
        Iterator<String> iterator = member.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            System.out.println("key:" + key + " value:" + member.get(key));
        }

        // hdel member sex age
        Long hdel = jedis.hdel("member", "sex", "age"); // hdel = 2

        // del member
        Long del = jedis.del("member"); // del = 1
    }

    /*判断是否存在*/
    @Test
    public void hexist() {
        jedis.hset("member", "username", "zhangsan");
        // hexists key field
        // 判断 username 在 member 中是否存在，存在为true，不存在为false
        Boolean isExists = jedis.hexists("member", "username"); // true
        jedis.del("member");
        isExists = jedis.hexists("member", "username"); // false
    }

    /*hash大小*/
    @Test
    public void hlen() {
        Map<String, String> map = new HashMap<>();
        map.put("sex", "male");
        map.put("age", "19");
        jedis.hmset("member", map);
        // hlen key
        // 即使没有 member 也不会报错，返回长度为0
        Long hlen = jedis.hlen("member"); // hlen = 2
        jedis.del("member");
    }

    /*获取所有的键或值*/
    @Test
    public void hkeyValues() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "zhangsan");
        map.put("password", "123456");
        jedis.hmset("member", map);
        // hkeys member
        Set<String> keys = jedis.hkeys("member");
        for (String key : keys) {
            System.out.println(key);
        }
        // hvals member
        List<String> values = jedis.hvals("member");
        for (String value : values) {
            System.out.println(value);
        }
        jedis.del("member");
        System.out.println(jedis.hkeys("member").size());
        System.out.println(jedis.hvals("member").size());
    }
}
