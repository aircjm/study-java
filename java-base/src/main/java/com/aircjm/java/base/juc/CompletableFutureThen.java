package com.aircjm.java.base.juc;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThen {

    public static void main(String[] args) {

        // then的使用

        // thenRun 不接受上个任务的结果，也没有返回值
        CompletableFuture.supplyAsync(() -> "supply 1")
                .thenRun(() -> {}).join();

        // thenAccept 从上个任务那边接受结果 但是不传递结果给下一个任务
        CompletableFuture.supplyAsync(() -> "supply 2")
                .thenAccept(supply -> {
                    System.out.println(supply);
                }).join();


        // thenAccept 从上个任务那边接受结果 也传递结果给下一个任务
        String join = CompletableFuture.supplyAsync(() -> "supply 3")
                .thenApply(supply -> {
                    System.out.println(supply);
                    return supply;
                }).join();

        System.out.println(join);



        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello world")
                .exceptionally(ex -> "errorResultA")
                .thenApply(resultA -> resultA + " resultB")
                .thenApply(resultB -> resultB + " resultC")
                .thenApply(resultC -> resultC + " resultD");

        System.out.println(future.join());

    }

}
