package com.aircjm.study.cloud.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;
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

    private Long id;

    private Long parentId;

    private String name;


}
