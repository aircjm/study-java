package com.aircjm.study.validate.controller;


import com.aircjm.study.validate.vo.SaveUserRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author aircjm
 */
@RestController
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

}
