package com.aircjm.java.base.util;

public class StringBufferUtil {


    public static void main(String[] args) {

        // StringBuffer 常规使用
        StringBuffer buffer = new StringBuffer();

        String hello = buffer.append("hello").toString();
        System.out.println(hello);

        // StringBuffer不支持传入null
        StringBuffer buffer2 = new StringBuffer(null);

        String hello2 = buffer2.append("hello").toString();
        System.out.println(hello2);

    }

}
