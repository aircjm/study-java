package com.aircjm.study.cloud.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class NodeTreeVo extends NodeVo {


    private List<NodeTreeVo> childList;

}
