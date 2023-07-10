package com.aircjm.java.base.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;

import java.util.List;

public class StringUtil {

    public static String reverseStrBy(String str, CharSequence charSequence) {
        List<String> splitList = StrUtil.split(str, charSequence);

        List<String> reverseList = Lists.reverse(splitList);
        return CollectionUtil.join(reverseList, charSequence);
    }

    public static void main(String[] args) {
        String str = reverseStrBy("hello, github", StrUtil.SPACE);
        System.out.println(str);
        String s = StrUtil.subBefore("hello aircjm, this is github website ", ",", false);
        System.out.println(reverseStrBy(s, StrUtil.SPACE));
    }
}
