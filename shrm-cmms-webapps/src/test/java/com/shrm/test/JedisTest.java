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
public class JedisTest {

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
