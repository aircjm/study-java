package com.aircjm.java.base.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("result1");
            return "result1";
        });
//2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> {
            System.out.println("result2");
        });
//3、先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
        CompletableFuture<String> cf = new CompletableFuture<>();

        CompletableFuture<Void> allOf = CompletableFuture.allOf(cf, cf1, cf2);


        try {
            allOf.get();
        } catch (Exception e) {

        }

//        cf.get();
//        cf1.get();
//        cf2.get();
//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
//            return "result1";
//        });
//
//        String s = cf1.get();


    }

}
