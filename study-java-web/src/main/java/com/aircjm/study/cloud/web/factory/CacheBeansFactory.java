package com.aircjm.study.cloud.web.factory;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 获取bean 并找出符合条件的bean(用于根据不同业务区分处理)
 *
 * @author aircjm
 */
@Service
public class CacheBeansFactory implements ApplicationContextAware {

    public static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    public static void setContext(ApplicationContext applicationContext) {
        CacheBeansFactory.applicationContext = applicationContext;
    }


    /**
     * 根据Bean名称获取实例
     *
     * @return bean实例
     * @throws BeansException 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBeanByName(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 根据类型获取实例
     *
     * @param type 类型
     * @return bean实例
     * @throws BeansException 异常
     */
    public static <T> T getBeanByType(Class<T> type) throws BeansException {
        return applicationContext.getBean(type);
    }
}
