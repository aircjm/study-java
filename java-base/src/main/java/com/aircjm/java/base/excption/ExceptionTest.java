package com.aircjm.java.base.excption;

import org.apache.tomcat.util.ExceptionUtils;

public class ExceptionTest {


    public static void main(String[] args) {

        try {
            throwAException();
        } catch (Exception e) {
        }
    }


    public static void throwAException() {
        throw new ClassCastException();
    }


}
