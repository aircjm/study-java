package com.aircjm.study.cloud.web.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class FutureTest {

    @Test
    public void testFuture() throws Exception {
        // 创建一个线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交一个任务
        Future<String> future = executor.submit(() -> {
            Thread.sleep(2000); // 模拟耗时操作
            return "hello world";
        });

        // 初始状态应该是未完成
        assertFalse(future.isDone());

        // 等待一段时间，确保任务完成
        TimeUnit.SECONDS.sleep(3);

        // 此时任务应该已经完成
        assertTrue(future.isDone());

        // 获取结果
        String result = future.get();
        assertEquals("hello world", result);

        // 关闭线程池
        executor.shutdown();
    }
}
