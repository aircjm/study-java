package com.aircjm.study.cloud.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author aircjm
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NodeVo {

    /**
     * id
     */
    private Long id;

    /**
     * 父节点id 0为根节点
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;


}
