package com.shrm.test;

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
public class JedisHashTest {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /*hash*/
    @Test
    public void hashSet() {
        // hset member name zhangsan
        jedis.hset("member", "name", "zhangsan");

        // hget member name
        System.out.println(jedis.hget("member", "name"));

        // hset member sex male age 19
        Map<String, String> map = new HashMap<>();
        map.put("sex", "male");
        map.put("age", "19");
        jedis.hmset("member", map);

        // hget member name age sex
        List<String> list = jedis.hmget("member", "name", "age", "sex");
        for (String s : list) {
            System.out.println(s);
        }

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
        Long hdel = jedis.hdel("member", "sex", "age");
        System.out.println(hdel); // 操作数量

        // del member
        Long del = jedis.del("member");
        System.out.println(del);
    }

    /*判断是否存在*/
    @Test
    public void hexist() {
        jedis.hset("member", "username", "zhangsan");
        // hexists member username
        Boolean hexists = jedis.hexists("member", "username");
        // 判断 username 在 member 中是否存在，存在为true，不存在为false
        System.out.println(hexists);
        Long member = jedis.del("member");
        System.out.println(member);
        System.out.println(jedis.hexists("member", "username"));
    }

    /*hash大小*/
    @Test
    public void hlen() {
        Map<String, String> map = new HashMap<>();
        map.put("sex", "male");
        map.put("age", "19");
        jedis.hmset("member", map);
        // hlen member
        Long member = jedis.hlen("member");
        System.out.println(member);
        jedis.del("member");
        // 即使没有 member 也不会报错，返回长度为0
        System.out.println(jedis.hlen("member"));
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
