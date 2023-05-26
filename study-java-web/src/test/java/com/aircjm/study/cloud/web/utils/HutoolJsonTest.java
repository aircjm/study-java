package com.aircjm.study.cloud.web.utils;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.UserResponse;
import com.rimlook.framework.core.pojo.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class HutoolJsonTest {





    @Test
    public void testJson() {
        Response<UserResponse> response= new Response<>();
        response.setData(new UserResponse("", 18, LocalDateTime.now()));
        String jsonStr = JSONUtil.toJsonStr(response);
        System.out.println(jsonStr);;


        Response<UserResponse> bean = JSONUtil.toBean(jsonStr, new TypeReference<Response<UserResponse>>() {
        }, false);
        System.out.println(JSONUtil.toJsonStr(bean));;

    }

}
