package com.aircjm.java.base.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Integer id;

    private BigDecimal price;

    private String name;

    private List<String> chapterList;


    public Book(String name, List<String> chapterList) {
        this.name = name;
        this.chapterList = chapterList;
    }


    public static List<Book> getTestBookList() {
        List<Book> list = new ArrayList<>();
        list.add(new Book(3, new BigDecimal("0.20"), "test24", null));
        list.add(new Book(4, new BigDecimal("2.30"), "test15", null));
        list.add(new Book(8, new BigDecimal("4.00"), "test52", null));
        list.add(new Book(5, new BigDecimal("4.00"), "test11", null));
        list.add(new Book(1, new BigDecimal("10.00"), "test1", null));
        list.add(new Book(2, new BigDecimal("4.00"), "test3", null));
        list.add(new Book(6, new BigDecimal("6.00"), "test3", null));
        list.add(new Book(7, new BigDecimal("5.00"), "test53", null));
        list.add(new Book(9, new BigDecimal("45.00"), "test25", null));
        return list;
    }

}
