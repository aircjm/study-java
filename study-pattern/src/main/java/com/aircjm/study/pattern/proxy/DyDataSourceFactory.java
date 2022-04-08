package com.aircjm.study.pattern.proxy;

import lombok.Data;

@Data
public class DyDataSourceFactory {


    public static final String DEFAULT_DATA_SOURCE_NAME = "dataSource";


    public static final ThreadLocal<String> local = new ThreadLocal<>();


    public void clear() {
        local.remove();
    }


    public void set(String dataSourceName) {
        local.set(dataSourceName);
    }


    public String get() {
        return local.get();
    }


    public void set(int year) {
        local.set("DB_" + year);
    }


}
