package com.aircjm.java.base.juc;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureHandle {

    public static void main(String[] args) {

        // 异常的处理


        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("hello runAsync");
                    return "hello";
                })
                .thenApply(item -> {
                    System.out.println("hello supply async");
//                    throw new RuntimeException("e");
                    return "hello";
                })
                .handle((o, throwable) -> {
                    System.out.println("未发生异常 返回值是:" + o);
                    System.out.println("发生异常了，异常信息为：" + throwable);
                    return throwable;
                })
                .thenRun(() -> {
                }).join();

        System.out.println("-----------------------------------------------------");


        // exceptionally 来处理异常
        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("hello runAsync");
                    return "hello";
                })
                .thenApply(item -> {
                    try {
                        System.out.println("hello supply async");
                        throw new Exception("custom exception");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .exceptionally(throwable -> {
                    System.out.println(throwable);
                    return throwable;
                })
                .thenRun(() -> {
                }).join();


        System.out.println("-----------------------------------------------------");


        // 如果出现异常，CompletableFuture会终止，后面的任务就不会再执行了
        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("hello runAsync");
                    return "hello";
                })
                .thenApply(item -> {
                    try {
                        System.out.println("hello supply async");
                        throw new Exception("custom exception");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .thenApply(item -> {
                    System.out.println(item);
                    return item;
                })
                .thenRun(() -> {
                }).join();

    }

}
