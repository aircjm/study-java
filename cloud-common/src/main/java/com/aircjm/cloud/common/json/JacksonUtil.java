package com.aircjm.cloud.common.json;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.IOException;

/**
 * @author admin
 */
public final class JacksonUtil {

    private static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);


    private static ObjectMapper objectMapper = ObjectMapperFactory.getDefaultObjectMapper();

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr   json字符串
     * @param valueType 类型
     * @return 返回值
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (StrUtil.isBlank(jsonStr)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            log.warn("readValue error: ", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> T readValue(String jsonStr, Class<T> valueType, Class contentClass) {
        JavaType type = objectMapper.getTypeFactory().constructParametricType(valueType, contentClass);
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (Exception e) {
            log.warn("readValue error: ", e);
        }
        return null;
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            log.warn("readValue error: ", e);
        }
        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param t   对象
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> String toJson(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (Exception e) {
            log.warn("readValue error: ", e);
        }
        return null;
    }

    /**
     * 根据 json 串和节点名返回节点
     */
    public static JsonNode getNode(String json, String nodeName) {
        try {
            JsonNode node = objectMapper.readTree(json);
            return node.get(nodeName);
        } catch (IOException e) {
            log.warn("readValue error: ", e);
        }
        return null;
    }
}
