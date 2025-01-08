package com.aircjm.java.base.juc;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class CompletableFutureDemo7 {

    private static Integer num = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("主线程开始");

        List<CompletableFuture<Integer>> tasks = Arrays.asList(
                add10(),
                multiplyBy10(),
                subtract10(),
                divideBy10()
        );

        List<Integer> results = tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        System.out.println(results);
    }

    private static CompletableFuture<Integer> add10() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("加 10 任务开始");
            num += 10;
            return num;
        });
    }

    private static CompletableFuture<Integer> multiplyBy10() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("乘以 10 任务开始");
            num *= 10;
            return num;
        });
    }

    private static CompletableFuture<Integer> subtract10() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("减以 10 任务开始");
            num -= 10;
            return num;
        });
    }

    private static CompletableFuture<Integer> divideBy10() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("除以 10 任务开始");
            num /= 10;
            return num;
        });
    }
}
