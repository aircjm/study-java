package com.aircjm.study.cloud.webconfig.factory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 缓存Bean工厂类
 * @author aircjm
 */
public class CacheBeansFactory implements ApplicationContextAware {

    /**
     * todo
     * @param clientClass
     * @return
     */
    public static Object getBeanByType(Class<?> clientClass) {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
