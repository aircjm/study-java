package com.aircjm.java.base.juc;

import com.google.common.collect.Lists;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CompletableFutureDemo2 {

    /**
     * 线程不安全
     */
    static volatile AtomicInteger a = new AtomicInteger(0);

    /**
     * 线程不安全的
     */
    static int b = 0;

    public static void main(String[] args) {

        ArrayList<@Nullable String> strList = Lists.newArrayList();

        for (int i = 0; i < 10000; i++) {
            strList.add("string " + i);
        }


        CopyOnWriteArrayList<String> safeList = new CopyOnWriteArrayList<>();

        List<CompletableFuture<Void>> list = strList.stream().map(item -> CompletableFuture.runAsync(() -> {
            System.out.println(item);
            safeList.add(item);
            b++;
            a.addAndGet(1);
        })).collect(Collectors.toList());


        CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()])).join();


        List<CompletableFuture<Void>> supplyList = strList.stream().map(item -> CompletableFuture.runAsync(() -> {
            System.out.println(item);
        })).collect(Collectors.toList());


        CompletableFuture.anyOf(supplyList.toArray(new CompletableFuture[supplyList.size()])).join();


        System.out.println(a);
        System.out.println(b);

        System.out.println(safeList.size());
    }
}
