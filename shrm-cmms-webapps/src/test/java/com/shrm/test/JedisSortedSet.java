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
        jedis.zadd("mysortedset", 3, "a"); // mysortedset 长度为仍为2，a的权值为3

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
     * 根据权重范围查询或删除
     * zremrangebyscore key start end
     * zrangebyscore key min max
     */
    @Test
    public void zrangebyscore() {
        jedis.del("mysortedset");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");

        // zremrangebyscore mysortedset 10 20
        Long delNum = jedis.zremrangeByScore("mysortedset", 11, 21);

        // zcount mysortedset 10 30
        Long remainNum = jedis.zcount("mysortedset", 10, 30); // mysortedset1 = 2

        // zrangebyscore mysortedset 0 30
        Set<String> mysortedset = jedis.zrangeByScore("mysortedset", 0, 30);

        jedis.del("mysortedset");
    }

    /**
     * 根据排序范围删除
     * zremrangebyrank key start end
     */
    @Test
    public void zrangebyrank() {
        jedis.del("mysortedset");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");

        // zremrangebyrank mysortedset 0 1
        Long delNum = jedis.zremrangeByRank("mysortedset", 0, 1); // delNum = 2
        // c
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);

        jedis.del("mysortedset");
    }

    /**
     * zincrby key score member
     */
    @Test
    public void zincrby() {
        jedis.del("mysortedset");

        jedis.zadd("mysortedset", 10, "a");
        jedis.zadd("mysortedset", 20, "b");
        jedis.zadd("mysortedset", 30, "c");

        Set<Tuple> mysortedset = jedis.zrangeWithScores("mysortedset", 0, -1);

        Double score = jedis.zincrby("mysortedset", 5, "a"); // score = 15.0

        jedis.del("mysortedset");
    }

    /**
     * 返回成员在集合中的排名
     * zrank mysortedset b
     */
    @Test
    public void zrank() {
        jedis.del("mysortedset");

        jedis.zadd("mysortedset",10,"a");
        jedis.zadd("mysortedset",20,"b");
        jedis.zadd("mysortedset",30,"c");

        // zrank mysortedset a
        Long memberRank = jedis.zrank("mysortedset", "a"); // memberRank = 0
        // zrevrank mysortedset c
        Long memberRevRank = jedis.zrevrank("mysortedset", "c"); // memberRevRank = 0

        jedis.del("mysortedset");
    }
}
