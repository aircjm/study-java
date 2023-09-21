package com.aircjm.java.base.java8;

import cn.hutool.json.JSONUtil;

import java.util.Comparator;
import java.util.List;

public class LambdaSorted {

    public static void main(String[] args) {
        List<Book> testBookList = Book.getTestBookList();
        System.out.println(JSONUtil.toJsonStr(testBookList));

        testBookList.sort(Comparator.comparing(Book::getPrice).thenComparing(Book::getId));
        System.out.println(JSONUtil.toJsonStr(testBookList));

        testBookList.forEach(item -> {
            item.setPrice(null);
        });

//        CollectionUtil.sort(testBookList,Comparator.comparing(Book::getPrice,
//                Comparator.nullsLast(Comparator.naturalOrder())) );
        testBookList.sort(Comparator.comparing(Book::getPrice,
                Comparator.nullsLast(Comparator.naturalOrder())));
//        testBookList.sort(Comparator.nullsLast(Comparator.comparing(Book::getPrice)));
        System.out.println(JSONUtil.toJsonStr(testBookList));
    }
}
