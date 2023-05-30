package com.aircjm.java.base.juc;

import java.util.concurrent.CompletableFuture;

/**
 * 两个任务并发执行 使用不同的API来实现不同的返回值和处理结果
 */
public class CompletableFutureTwoReturn {

    public static void main(String[] args) {

        acceptBoth();

        thenCombine();

        runAfterBoth();
    }


    // 并行后的任务处理不需要返回值
    public static void acceptBoth() {
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> bsync = CompletableFuture.supplyAsync(() -> "world");

        Void join = async.thenAcceptBoth(bsync, (a, b) -> {
            System.out.println(a);
            System.out.println(b);
        }).join();

    }


    // 并行后的任务处理需要返回值
    public static void thenCombine() {
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> bsync = CompletableFuture.supplyAsync(() -> "world");

        String join = async.thenCombine(bsync, (a, b) -> {
            System.out.println(a);
            System.out.println(b);
            return a + b;
        }).join();
        System.out.println(join);
    }



    // 并行后的任务处理后需要入参
    public static void runAfterBoth() {
        CompletableFuture<String> async = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> bsync = CompletableFuture.supplyAsync(() -> "world");

        Void join = async.runAfterBoth(bsync, () -> {
            System.out.println("无 入参");
        }).join();
        System.out.println(join);
    }




}
