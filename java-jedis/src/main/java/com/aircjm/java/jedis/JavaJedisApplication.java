package com.aircjm.java.jedis;


import redis.clients.jedis.Jedis;

import java.util.Set;

public class JavaJedisApplication {



    public static void testJedisConnection () {
        // 连接Redis（第一个参数是Redis的IP地址，第二个参数是Redis的端口号）
        Jedis jedis = getJedis();
// 尝试操作Redis
        jedis.set("msg", "Hello World!");
        String msg = jedis.get("msg");
        System.out.println(msg);	// 打印"Hello World!"
// 关闭Redis连接
        jedis.close();
    }



    public static void testJedisConnection2 () {
        // 连接Redis（第一个参数是Redis的IP地址，第二个参数是Redis的端口号）
        Jedis jedis = getJedis();
// 尝试操作Redis

        Set<String> smembers = jedis.smembers("key value");

        System.out.println(smembers.toArray()[smembers.size()-1]);
        System.out.println(smembers.size());	// 打印"Hello World!"
// 关闭Redis连接
        jedis.close();
    }




    private static Jedis getJedis() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("password");
        return jedis;
    }

    public static void main(String[] args) {
        System.out.println("hello world");

        testJedisConnection2();
    }

}
