package com.aircjm.study.cloud.web.utils;

import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.TreeNode;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

@Slf4j
class TreeUtilsTest {


    @Test
    public void testBuildTree() {

        List<ResourceListVO> resourceListVOList = Lists.newArrayList();
        resourceListVOList.add(new ResourceListVO(1L, 0L, 1, "测试1", "", "", 1));
        resourceListVOList.add(new ResourceListVO(2L, 0L, 1, "测试2", "", "", 1));
        resourceListVOList.add(new ResourceListVO(3L, 1L, 1, "测试3", "", "", 1));
        resourceListVOList.add(new ResourceListVO(4L, 1L, 1, "测试4", "", "", 1));


        List<ResourceListVO> resourceListVOS = TreeUtils.generateTrees(resourceListVOList);
        System.out.println(JSONUtil.toJsonStr(resourceListVOS));
    }




    @Test
    public void testBuildNullAbleTree() {

        List<ResourceNullVO> resourceListVOList = Lists.newArrayList();
        resourceListVOList.add(new ResourceNullVO(1L, null, 1, "测试1", "", "", 1));
        resourceListVOList.add(new ResourceNullVO(2L, null, 1, "测试2", "", "", 1));
        resourceListVOList.add(new ResourceNullVO(3L, 2L, 1, "测试3", "", "", 1));
        resourceListVOList.add(new ResourceNullVO(4L, 1L, 1, "测试4", "", "", 1));
        resourceListVOList.add(new ResourceNullVO(5L, 3L, 1, "测试5", "", "", 1));
        resourceListVOList.add(new ResourceNullVO(6L, 5L, 1, "测试6", "", "", 1));


        List<ResourceNullVO> resourceListVOS = TreeUtils.generateTrees(resourceListVOList);
        System.out.println(JSONUtil.toJsonStr(resourceListVOS));
    }





}


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ResourceListVO implements TreeNode<Long> {
    private Long id;

    private Long pid;

    private Integer type;

    private String name;

    private String icon;

    private String code;

    private Integer status;

    private List<ResourceListVO> children;


    public ResourceListVO(Long id, Long pid, Integer type, String name, String icon, String code, Integer status) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.code = code;
        this.status = status;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long parentId() {
        return this.pid;
    }

    @Override
    public boolean root() {
        return Objects.equals(this.pid, 0L);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }
}




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class ResourceNullVO implements TreeNode<Long> {
    private Long id;

    private Long pid;

    private Integer type;

    private String name;

    private String icon;

    private String code;

    private Integer status;

    private List<ResourceListVO> children;


    public ResourceNullVO(Long id, Long pid, Integer type, String name, String icon, String code, Integer status) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.name = name;
        this.icon = icon;
        this.code = code;
        this.status = status;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long parentId() {
        return this.pid;
    }

    @Override
    public boolean root() {
        return Objects.isNull(this.pid);
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }
}