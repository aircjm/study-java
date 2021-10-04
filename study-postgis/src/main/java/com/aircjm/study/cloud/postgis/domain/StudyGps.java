package com.aircjm.study.cloud.postgis.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.postgis.Point;

import java.util.Date;

/**
 * @author aircjm
 */
@Data
@TableName("study_gps")
public class StudyGps {


    /**
     * 时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            timezone = "+08")
    private Date time;
    /**
     * 设备id
     */
    private String devId;
    /**
     * 位置
     */
    private Point location;
    /**
     * 卫星定位数
     */
    private int gpsNum;
    /**
     * GPS定位信息
     */
    private String gpsType;
    /**
     * 对地真北航向角
     */
    private double azimuth;

    /**
     * 地面速率
     */
    private double gndRate;
}
