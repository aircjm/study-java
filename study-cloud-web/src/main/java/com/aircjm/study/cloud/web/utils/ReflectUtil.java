package com.aircjm.study.cloud.web.utils;

import com.aircjm.cloud.common.exceptions.Assert;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 反射工具类
 * @author aircjm
 */
@Slf4j
public class ReflectUtil {


    /**
     * 获取指定类的所有声明的字段，包括父类
     *
     * @param targetClass 指定的类型
     * @return Field集合
     */
    public static List<Field> getAllFields(Class<?> targetClass) {
        if (targetClass == null) {
            return Collections.emptyList();
        }
        List<Field> fields = Lists.newArrayList();
        while (targetClass != Object.class) {
            fields.addAll(Arrays.asList(targetClass.getDeclaredFields()));
            targetClass = targetClass.getSuperclass();
        }
        return fields;
    }

    /**
     * 获取指定类的所有声明的字段，不包括父类
     *
     * @param targetClass 指定的类型
     * @return Field集合
     */
    public static List<Field> getAllFieldsWithoutParent(Class<?> targetClass) {
        if (targetClass == null) {
            return Collections.emptyList();
        }
        List<Field> fields = Lists.newArrayList();
        fields.addAll(Arrays.asList(targetClass.getDeclaredFields()));
        return fields;
    }

    /**
     * 获取所有属性值
     * <p>
     * getDeclaredField是可以获取一个类的所有字段
     * getField只能获取类的public字段
     *
     * @param clazz 指定的对象
     * @return 返回值
     */
    public static List<String> getAllFieldValue(Class<?> clazz) {
        List<String> values = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                values.add((String) field.get(field.getName()));
            } catch (IllegalAccessException e) {
                log.warn("getAllFieldValue.IllegalAccessException", e);
                return Lists.newArrayList();
            }
        }
        return values;
    }

    /**
     * 获取所有属性值-返回Object类型
     * <p>
     * getDeclaredField是可以获取一个类的所有字段
     * getField只能获取类的public字段
     *
     * @param t 指定的对象
     * @return 返回值
     */
    public static <T> List<Object> getAllFieldObjectValue(T t) {
        List<Object> values = Lists.newArrayList();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                values.add(field.get(t));
            } catch (Exception e) {
                log.warn("getAllFieldObjectValue error", e);
                return Lists.newArrayList();
            }
        }
        return values;
    }

    /**
     * 根据字段名称获取字段值
     *
     * @param t         对象
     * @param fieldName 字段名称
     * @param <T>       类型
     * @return 字段值
     */
    public static <T> Object getFieldValueByName(T t, String fieldName) {
        if (t == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        try {
            List<Field> fields = ReflectUtil.getAllFields(t.getClass());
            for (Field field : fields) {
                if (field.getName().equalsIgnoreCase(fieldName)) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), t.getClass());
                    return propertyDescriptor.getReadMethod().invoke(t);
                }
            }
        } catch (Exception e) {
            log.warn("getFieldByName error.", e);
            return null;
        }
        return null;
    }

    /**
     * 根据字段名称获取字段值
     *
     * @param obj   对象实例
     * @param field 属性
     * @return 字段值
     */
    public static Object getFieldValueByName(Object obj, Field field) {
        Assert.requireNonNull(obj, "obj不能为空");
        Assert.requireNonNull(field, "field不能为空");
        try {
            if (!Modifier.isFinal(field.getModifiers())) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), obj.getClass());
                return propertyDescriptor.getReadMethod().invoke(obj);
            }
        } catch (Exception e) {
            log.warn("ReflectUtil.getFieldValueByName error", e);
        }
        return null;
    }

    /**
     * 根据字段名称设置字段值
     *
     * @param obj   对象实例
     * @param field 属性
     * @param value 值
     */
    public static void setFieldValueByName(Object obj, Field field, Object value) {
        Assert.requireNonNull(obj, "class不能为空");
        Assert.requireNonNull(field, "field不能为空");
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), obj.getClass());
            propertyDescriptor.getWriteMethod().invoke(obj, value);
        } catch (Exception e) {
            log.warn("ReflectUtil.getFieldValueByName error", e);
        }
    }



    /**
     * 获取含有指定注解的字段Field
     *
     * @param t          原始对象
     * @param annotation 注解对象类型
     * @param <T>        原始对象类型
     * @param <A>        注解对象类型
     * @return Field集合
     */
    public static <T, A> List<Field> getAnnotationField(T t, Class<? extends Annotation> annotation) {
        if (Objects.isNull(t) || Objects.isNull(annotation)) {
            return null;
        }
        List<Field> list = getAllFields(t.getClass());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Field> result = Lists.newArrayList();
        for (Field field : list) {
            if (field.isAnnotationPresent(annotation)) {
                result.add(field);
            }
        }
        return result;
    }

    /**
     * 为所有字段设置相同的值
     *
     * @param t   原始对象
     * @param v   值
     * @param <T> 类型
     * @param <V> 类型
     */
    public static <T, V> void setAllFieldsSameValue(T t, V v) {
        List<Field> fields = ReflectUtil.getAllFieldsWithoutParent(t.getClass());
        if (CollectionUtils.isNotEmpty(fields)) {
            fields.forEach(field -> ReflectUtil.setFieldValueByName(t, field, v));
        }
    }
}
