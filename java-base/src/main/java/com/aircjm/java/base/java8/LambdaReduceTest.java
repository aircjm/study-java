package com.aircjm.java.base.java8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LambdaReduceTest {
    public static void main(String[] args) {
        List<Book> testBookList = Book.getTestBookList();
        double sum = testBookList.stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        System.out.println(sum);


        BigDecimal avg =
                testBookList.stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(testBookList.size()), 5, RoundingMode.HALF_UP);
        System.out.println(avg);


    }
}
