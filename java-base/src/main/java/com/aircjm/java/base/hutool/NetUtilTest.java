package com.aircjm.java.base.hutool;

import cn.hutool.core.net.NetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetUtilTest {


    public static void main(String[] args) {
        String localHostName = NetUtil.getLocalHostName();
        log.info(localHostName);
    }
}
