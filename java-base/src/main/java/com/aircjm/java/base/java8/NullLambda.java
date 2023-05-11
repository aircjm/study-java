package com.aircjm.java.base.java8;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NullLambda {


    public static void main(String[] args) {
        // lambda不支持处理null
//        nullLambda(null);
        // lambda支持empty集合
        nullLambda(new ArrayList<Book>());
    }


    public static void nullLambda(List<Book> list) {
        List<String> stringList = list.stream().map(Book::getName).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonStr(stringList));
    }

}
