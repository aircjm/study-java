package com.aircjm.study.cloud.web.vo;

import lombok.Data;

import java.util.List;

/**
 * @author aircjm
 */
@Data
public class NodeTreeVo extends NodeVo {


    /**
     * 子集合数据
     */
    private List<NodeTreeVo> childList;

}
