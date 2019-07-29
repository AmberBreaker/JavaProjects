package com.shrm.test;

import com.shrm.utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * list存储
 */
public class JedisListSavingTest {

    private static Jedis jedis;

    static {
        jedis = JedisPoolUtils.getJedis();
    }

    /*
     * list存取
     */
    @Test
    public void lrpush() {
        /*左存储*/
        // lpush mylist 1 2 3   存储结果为: 3 2 1
        Long lpush = jedis.lpush("mylist", "1", "2", "3");
        // 右存储
        // rpush mylist a b c   存储结果为: 3 2 1 a b c
        Long rpush = jedis.rpush("mylist", "a", "b", "c");

        /*查看列表*/
        // lrange mylist 0 4    查询从0开始，mylist中前五个值: 3 2 1 a b
        List<String> mylist1 = jedis.lrange("mylist", 0, 4);
        // lrange mylist 0 -1   查询mylist中从第一个到最后一个值: 3 2 1 a b c
        List<String> mylist2 = jedis.lrange("mylist", 0, -1);
    }

    @Test
    public void lrpushx() {
        // 报错，因为没有test集合
        // jedis.lpushx("test", "test1", "test2");
        // lpushx mylist x
        Long lpushx = jedis.lpushx("mylist", "x");
        // rpushx mylist y
        Long rpushx = jedis.rpushx("mylist", "y");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        for (String str : mylist) {
            System.out.print(str + " ");
        }
    }

    /*
     * list弹出
     */
    @Test
    public void lrpop() {
        // lpop mylist  从mylist最左侧弹出一个元素，若为空，返回null(nil)
        String lpop = jedis.lpop("mylist");
        // rpop mylist  从mylist最右侧弹出一个元素，若为空，返回null(nil)
        String rpop = jedis.rpop("mylist");
        System.out.println(lpop + " === " + rpop);
        // mylist 最左侧与最右侧数据消失
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        for (String str : mylist) {
            System.out.print(str + " ");
        }
    }

    /*
     * 移除指定数量的指定值
     */
    @Test
    public void leftRemove() {
        // 3 2 1 3 2 1 1 2 3
        jedis.lpush("mylist", "1", "2", "3");
        jedis.lpush("mylist", "1", "2", "3");
        jedis.rpush("mylist", "1", "2", "3");

        // 从左向右，删除值为3的数据，删除数量为2
        jedis.lrem("mylist", 2, "3"); // 2 1 2 1 1 2 3
        // 从右向左，删除值为2的数据，删除数量为2
        jedis.lrem("mylist", -2, "2"); // 2 1 1 1 3
        // 删除所有值为1的数据
        jedis.lrem("mylist", 0, "1"); // 2 3
        jedis.del("mylist");
    }

    /*
     * 修改链表中指定脚标对应的值(从0开始)
     */
    @Test
    public void leftSet() {
        jedis.lpush("mylist", "1", "2", "3");
        // lset mylist 0 11 (0代表第一个)
        jedis.lset("mylist", 0, "11"); // 11 2 3
        // lset mylist -1 33 (-1代表最后一个)
        jedis.lset("mylist", -1, "33"); // 11 2 33
        jedis.del("mylist");
    }
}
