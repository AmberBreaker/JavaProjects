package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Set
 */
public class JedisSetTest {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /*新增*/
    @Test
    public void setAdd() {
        jedis.del("myset");

        // sadd myset a b c  返回插入成功的数量, s1 = 3
        Long s1 = jedis.sadd("myset", "a", "b", "c"); // a b c
        // 出现重复value时不会报错也不会插入, s2 = 1
        Long s2 = jedis.sadd("myset", "b", "c", "d"); // a b c d

        // smembers myset
        Set<String> myset = jedis.smembers("myset");

        jedis.del("myset");
    }

    /*移除*/
    @Test
    public void setRemove() {
        jedis.del("myset");

        jedis.sadd("myset", "a", "b", "c"); // a b c

        // 移除set中的值，若该值不存在，也不会报错，返回成功移除的个数(srem = 1)
        Long srem = jedis.srem("myset", "a", "1"); // a b

        jedis.del("myset");
    }

    /*判断是否存在*/
    @Test
    public void setIsmember() {
        jedis.del("myset");

        jedis.sadd("myset", "a", "b", "c");

        // sismember myset a
        Boolean s = jedis.sismember("myset", "a");

        jedis.del("myset");
    }

    /*集合运算*/
    @Test
    public void setOperation() {
        jedis.del("myset1");
        jedis.del("myset2");

        jedis.sadd("myset1", "a", "b", "c", "d"); // a b c d
        jedis.sadd("myset2", "c", "d", "e", "f"); // c d e f

        // 差集: sdiff key1 key2 ...
        Set<String> sdiff = jedis.sdiff("myset1", "myset2"); // a b

        // 交集: sinter key1 key2 ...
        Set<String> sinter = jedis.sinter("myset1", "myset2"); // c d

        // 并集: sunion key1 key2 key3 ...
        Set<String> sunion = jedis.sunion("myset1", "myset2"); // a b c d e f

        jedis.del("myset1");
        jedis.del("myset2");
    }

    /*集合运算并将结果存储*/
    @Test
    public void setOperationAndStore() {
        jedis.del("myset1");
        jedis.del("myset2");

        jedis.sadd("myset1", "a", "b", "c", "d"); // a b c d
        jedis.sadd("myset2", "c", "d", "e", "f"); // c d e f

        // sdiffstore destination key1 key2 ...
        Long sdiffstore = jedis.sdiffstore("myset3", "myset1", "myset2");
        // sinterstore destination key1 key2 ...
        Long sinterstore = jedis.sinterstore("myset4", "myset1", "myset2");
        // sunionstore destination key1 key2 ...
        Long sunionstore = jedis.sunionstore("myset5", "myset1", "myset2");


        jedis.del("myset1");
        jedis.del("myset2");
        jedis.del("myset3");
        jedis.del("myset4");
        jedis.del("myset5");
    }

    /*查看set的长度*/
    @Test
    public void scard() {
        jedis.del("myset");
        jedis.sadd("myset","a","b","c");

        // scard key
        Long myset = jedis.scard("myset"); // 3

        jedis.del("myset");
    }

    /*随机获得set中的一个成员*/
    @Test
    public void randomMember() {
        jedis.del("myset");
        jedis.sadd("myset","a","b","c");

        // srandmember key
        String myset = jedis.srandmember("myset");

        // 没有从set中抽离出去
        Set<String> myset1 = jedis.smembers("myset"); // a b c
    }
}
