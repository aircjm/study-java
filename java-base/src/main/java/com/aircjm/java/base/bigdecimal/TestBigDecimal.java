package com.aircjm.java.base.bigdecimal;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {


    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("10000.9900234234324234");

        BigDecimal divide = NumberUtil.div(bigDecimal, new BigDecimal("1.402"), 4, RoundingMode.HALF_UP);
//        BigDecimal divide = bigDecimal.divide(new BigDecimal("33.22"),  RoundingMode.UP);
        System.out.println(divide);


        BigDecimal add = NumberUtil.add(bigDecimal, divide);
        System.out.println(add);

    }

}
