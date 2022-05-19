package com.aircjm.study.cloud.web.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.aircjm.cloud.common.exceptions.BizException;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;


/**
 * EasyExcel工具类
 *
 * @author aircjm
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 解析Excel数据
     *
     * @param file  文件
     * @param clazz 对象class
     * @param <T>   类型
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            throw new BizException("对象为空");
        }
        List<T> data;
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            data = importExcel(inputStream, clazz);
        } catch (Exception e) {
            throw new BizException("解析excel异常", e);
        } finally {
            if (Objects.nonNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.warn("关流失败");
                }
            }
        }
        return data;
    }

    /**
     * 解析Excel数据
     *
     * @param inputStream 输入流
     * @param clazz       class
     * @param <T>         类型
     * @return 返回
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> importExcel(InputStream inputStream, Class<?> clazz) {
        if (Objects.isNull(inputStream)) {
            throw new BizException("inputStream为空");
        }
        List<T> data;
        try {
            BaseImportEvo baseImportEvo = new BaseImportEvo(System.currentTimeMillis());
            EasyExcel.read(inputStream, clazz, baseImportEvo).sheet().doRead();
            data = baseImportEvo.getData();
        } catch (Exception e) {
            throw new BizException("解析Excel失败", e);
        }
        if (CollectionUtil.isEmpty(data)) {
            throw new BizException("解析结果集为空");
        }
        return data;
    }


    /**
     * 获取excel文件
     *
     * @param fileName 文件名称
     * @return oss返回的url
     */
    public static String createFile(String fileName, Class<?> clazz, List<?> data) {
        String filePath = Strings.EMPTY;
        try {
            filePath = System.getProperty("user.dir") + "/excel/data/" + fileName + ".xls";
            FileUtil.touch(filePath);
            // 创建文件路径
            // 导入数据,生成Excel
            EasyExcel.write(filePath, clazz).sheet().doWrite(data);
        } catch (Exception e) {
            log.warn("EasyExcelUtil.uploadFile error", e);
        }
        return filePath;
    }


}
