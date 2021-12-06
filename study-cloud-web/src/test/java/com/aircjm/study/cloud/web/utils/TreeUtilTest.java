package com.aircjm.study.cloud.web.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.NodeTreeVo;
import com.aircjm.study.cloud.web.vo.NodeVo;
import com.aircjm.study.cloud.web.vo.UserEvo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
class TreeUtilTest {

    /**
     * 获取一个随机树结构
     *
     * @return 树平铺list
     */
    private List<NodeVo> getNodeList() {
        List<NodeVo> nodeVoList = Lists.newArrayList();
        for (long i = 1L; i < 100; i++) {
            NodeVo nodeVo = NodeVo.builder().id(i).name("序号为：" + i).parentId(ThreadLocalRandom.current().nextLong(50)).build();
            if (i % 10 == 0) {
                nodeVo.setParentId(0L);
            }
            nodeVoList.add(nodeVo);

        }
        log.info("原始平铺结构树数据为： {}", JSONUtil.toJsonStr(nodeVoList));
        return nodeVoList;

    }


    @Test
    void treeTest() {
        List<NodeVo> nodeVoList = getNodeList();
        log.info("开始转化对应的树结构---------------->\n\n\n");
        List<NodeTreeVo> treeVoList = nodeVoList.stream().filter(item -> item.getParentId() == 0).map(nodeVo -> {
            NodeTreeVo nodeTreeVo = BeanUtil.toBean(nodeVo, NodeTreeVo.class);
            nodeTreeVo.setChildList(addTreeNode(nodeTreeVo, nodeVoList));
            return nodeTreeVo;
        }).collect(Collectors.toList());

        log.info("树结构为：{}", JSONUtil.toJsonStr(treeVoList));


    }

    private List<NodeTreeVo> addTreeNode(NodeTreeVo nodeTreeVo, List<NodeVo> all) {
        return all.stream()
                .filter(nodeVo -> {
                    return nodeVo.getParentId().equals(nodeTreeVo.getId());
                })
                .map(item -> BeanUtil.toBean(item, NodeTreeVo.class))
                .map(m -> {
                    m.setChildList(addTreeNode(m, all));
                    return m;
                }).collect(Collectors.toList());


    }
}
