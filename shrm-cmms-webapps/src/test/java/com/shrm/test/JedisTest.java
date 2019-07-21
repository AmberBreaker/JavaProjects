package com.shrm.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    // 通过java程序访问redis数据库
    @Test
    public void fun01() {
        Jedis jedis = new Jedis("47.105.108.215", 6379);
        jedis.set("username","zhangsan");
        System.out.println(jedis.get("username"));
        jedis.set("username","lisi");
        System.out.println(jedis.get("username"));
    }

}
