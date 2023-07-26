package com.aircjm.java.base.java8;

import com.aircjm.java.base.vo.UserVO;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Collectors.toMap的坑
 */
public class CollectionToMap {

    public static void main(String[] args) {
        List<UserVO> testUserVOList = getTestUserVOList();

        // key不能重复 null也是重复的
//        Map<Integer, UserVO> userVOMap = testUserVOList.stream().collect(Collectors.toMap(UserVO::getSex, Function.identity()));


        // value不能为空
//        Map<String, Integer> userVOMap = testUserVOList.stream().collect(Collectors.toMap(UserVO::getName, UserVO::getSex));

        // Exception in thread "main" java.lang.NullPointerException
        //	at java.util.HashMap.merge(HashMap.java:1226)
        //	at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320)
        //	at java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
        //	at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1384)
        //	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:482)
        //	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        //	at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
        //	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //	at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:566)
        //	at com.aircjm.java.base.java8.CollectionToMap.main(CollectionToMap.java:26)

    }



    public static List<UserVO> getTestUserVOList() {
        List<UserVO> userVOList = Lists.newArrayList();
        userVOList.add(UserVO.builder().name("hello").age(12).build());
        userVOList.add(UserVO.builder().name("world").sex(1).build());
        userVOList.add(UserVO.builder().name("java").age(14).build());
        return userVOList;
    }

}
