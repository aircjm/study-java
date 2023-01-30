package com.aircjm.java.base.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMap {
    public static void main(String[] args) {
        // flat list.stream()将两级集合打平到一级
        List<List<String>> listOfListOfString = new ArrayList<>();
        listOfListOfString.add(Arrays.asList("master", "visa"));
        listOfListOfString.add(Arrays.asList("flying", "reward"));
        listOfListOfString.add(Arrays.asList("apple pay", "samsung pay"));
        List<String> listOfJustStrings = listOfListOfString.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println("list of cards: " + listOfJustStrings);
        List<List<Integer>> listOfListOfInts = new ArrayList<>();

        listOfListOfInts.add(Arrays.asList(2, 4));
        listOfListOfInts.add(Arrays.asList(3, 9));
        listOfListOfInts.add(Arrays.asList(4, 16));
        List<Integer> listOfIntegers = listOfListOfInts.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        System.out.println("list of integers : " + listOfIntegers);

        Book effectiveJava = new Book("Effective Java", Arrays.asList("item 1", "item 2"));
        Book cleanCode = new Book("Clean Code", Arrays.asList("chapter 1", "chapter 2"));
        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(effectiveJava);
        listOfBooks.add(cleanCode);
        List<String> listOfChapters = listOfBooks.stream().flatMap(book -> book.getChapterList().stream()).collect(Collectors.toList());
        System.out.println("list of books : " + listOfBooks);
        System.out.println("list of chapters: " + listOfChapters);
    }
}




