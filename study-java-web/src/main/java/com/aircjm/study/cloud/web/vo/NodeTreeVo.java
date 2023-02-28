package com.aircjm.study.cloud.web.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author aircjm
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class NodeTreeVo extends NodeVo {


    /**
     * 子集合数据
     */
    private List<NodeTreeVo> childList;

}
