package com.aircjm.study.cloud.web.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.validate.constants.ValidateGroup;
import com.aircjm.study.validate.util.ValidatorUtil;
import com.aircjm.study.validate.vo.SaveOrderRequest;
import com.aircjm.study.validate.vo.SaveUserRequest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author aircjm
 */
@RestController
@Slf4j
@RequestMapping(value = "/validate")
public class ValidController {


    @GetMapping("/test")
    public String test() {
        return "success";
    }


    @GetMapping("/methodParamCheck")
    public String methodParamCheck(@Valid String code) {
        return code;
    }


    @Valid
    @GetMapping("/testParamValid")
    public String testParamValid(@RequestParam @NotNull Long code) {
        return "success";
    }


    @PostMapping("/saveUser")
    public String saveUser(@Valid @RequestBody SaveUserRequest request) {
        return "success";
    }


    @PostMapping("/updateOrder")
    public String updateOrder(@Valid @RequestBody SaveOrderRequest request) throws Exception {
        List<String> validate = Lists.newArrayList();
        if (request.getOrderType() == 1) {
            validate = ValidatorUtil.validate(request.getOrderVo(), ValidateGroup.SizeGroup.class);
            log.info("validate json is :{}", validate);
        } else if (request.getOrderType() == 2) {
            validate = ValidatorUtil.validate(request.getOrderVo(), ValidateGroup.UnSizeGroup.class);
            log.info("validate json is :{}", validate);
        } else {
            validate = ValidatorUtil.validate(request.getOrderVo());
            log.info("validate json is :{}", validate);
        }

        if (CollectionUtil.isNotEmpty(validate)) {
            throw new ConstraintViolationException(JSONUtil.toJsonStr(validate), null);
        }


        return "success";
    }


}
