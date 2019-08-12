package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * sorted-set
 */
public class JedisSortedSet {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /**
     * zadd key score member 添加元素
     * zscore member 查看权重
     * zcard key 查看集合中的数量
     * zrem key [member]
     */
    @Test
    public void zaddAndRem() {
        jedis.zrem("mysortedset", "a", "b");
        // zadd mysortedset 1 a
        Long a = jedis.zadd("mysortedset", 1, "a"); // a = 1
        Long b = jedis.zadd("mysortedset", 2, "b"); // b = 1

        // zscore mysortedset a
        Double zscore = jedis.zscore("mysortedset", "a"); // zscore = 1.0
        // zcard mysortedset
        Long zcard = jedis.zcard("mysortedset"); // zcard = 2

        // zrem mysortedset a b
        jedis.zrem("mysortedset", "a", "b");
    }

    /**
     * zrange key start end 获取元素
     * zrange key start end withscores 获取元素以及权值
     */
    @Test
    public void zrange() {
        jedis.zrem("mysortedset", "a", "b", "c");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");
        // zrange mysortedset 0 -1 // 0 ~ -1即为查询所有
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        for (String str : mysortedset) {
            // a b c
            System.out.print(str + " ");
        }
        System.out.println();

        // zrange mysortedset 0 -1 withscores
        Set<Tuple> mysortedset1 = jedis.zrangeWithScores("mysortedset", 0, -1);
        for (Tuple tuple : mysortedset1) {
            // a 10.0
            // b 20.0
            // c 30.0
            System.out.println(tuple.getElement() + " " + tuple.getScore() + " ");
        }

        jedis.zrem("mysortedset", "a", "b", "c");
    }

    /**
     * zrevrange key start end 按照权重倒序获取元素
     * zrevrange key start end withscores 按照权重倒序获取元素以及权值
     */
    @Test
    public void zrevrange() {
        jedis.zrem("mysortedset", "a", "b", "c");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");

        // zrevrange mysortedset 0 -1
        Set<String> mysortedset = jedis.zrevrange("mysortedset", 0, -1);

        for (String str : mysortedset) {
            System.out.print(str + " ");
        }
        System.out.println();

        // zrevrange mysortedset 0 -1 withscores
        Set<Tuple> mysortedset1 = jedis.zrevrangeWithScores("mysortedset", 0, -1);

        for (Tuple tuple : mysortedset1) {
            System.out.println(tuple.getElement() + " " + tuple.getScore());
        }

        jedis.zrem("mysortedset", "a", "b", "c");
    }

    /**
     *
     */
    @Test
    public void zremrangebyrank() {
        jedis.del("mysortedset");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");


        Long delNum = jedis.zremrangeByRank("mysortedset", 9, 21);

        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);


        jedis.del("mysortedset");
    }
}
