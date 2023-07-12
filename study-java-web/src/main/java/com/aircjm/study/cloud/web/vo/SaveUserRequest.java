package com.aircjm.study.cloud.web.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SaveUserRequest {


    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;

    @NotEmpty(message = "电话不能为空")
    private List<String> phone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
}
