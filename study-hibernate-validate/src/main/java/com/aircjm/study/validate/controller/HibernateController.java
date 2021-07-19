package com.aircjm.study.validate.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/validate")
public class HibernateController {


    @GetMapping("/test")
    public String test() {
        return "success";
    }


    @GetMapping("/methodParamCheck")
    public String methodParamCheck(@NotBlank String code) {
        return code;
    }

}
