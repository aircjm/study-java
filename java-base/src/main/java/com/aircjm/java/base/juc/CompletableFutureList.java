package com.aircjm.java.base.juc;

import com.aircjm.java.base.java8.Book;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureList {

    public static void main(String[] args) {
        List<Book> testBookList = Book.getTestBookList();

        long beginTime = System.currentTimeMillis();
//        testBookList.stream().map(item -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return item.getId();
//        }).collect(Collectors.toList());


        List<CompletableFuture<Integer>> futureList = testBookList.stream().map(item -> CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return item.getId();

        })).collect(Collectors.toList());


       List<Integer> collect =
               futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - beginTime);
    }

}
