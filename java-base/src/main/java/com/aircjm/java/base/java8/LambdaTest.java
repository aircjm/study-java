package com.aircjm.java.base.java8;

import com.google.common.collect.Lists;

import java.util.List;

public class LambdaTest {


    public List<Book> getBookList() {
        List<Book>  bookList =  Lists.newArrayList();
        bookList.add(new Book("hello", null));
        bookList.add(new Book("world", null));
        bookList.add(new Book("java", null));
        bookList.add(new Book("jvm", null));


        return bookList;
    }


    public static void main(String[] args) {
        List<Book> bookList = Lists.newArrayList();

        bookList.forEach(item -> {
            System.out.println(item.getName());
        });
    }


}
