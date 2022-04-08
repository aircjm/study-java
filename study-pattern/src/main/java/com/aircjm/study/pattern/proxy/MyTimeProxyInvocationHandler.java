package com.aircjm.study.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyTimeProxyInvocationHandler implements InvocationHandler {


    private Object target;


    public MyTimeProxyInvocationHandler(Object target) {
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始代理");
        Object invoke = method.invoke(target, args);
        System.out.println("结束代理");

        return invoke;
    }
}
