package com.aircjm.study.cloud.web.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.web.vo.NodeTreeVo;
import com.aircjm.study.cloud.web.vo.NodeVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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



    @Test
    void treeToMapTest() {
        List<NodeVo> nodeVoList = getNodeList();
        log.info("开始转化对应的树结构---------------->\n\n\n");
        List<NodeTreeVo> treeVoList = nodeVoList.stream().filter(item -> item.getParentId() == 0).map(nodeVo -> {
            NodeTreeVo nodeTreeVo = BeanUtil.toBean(nodeVo, NodeTreeVo.class);
            nodeTreeVo.setChildList(addTreeNode(nodeTreeVo, nodeVoList));
            return nodeTreeVo;
        }).collect(Collectors.toList());


        log.info("\n\n\n需要将节点全部平铺到第一层map上");

        HashMap<Long, List<NodeVo>> nodeMap = Maps.newHashMap();
        for (NodeTreeVo nodeTreeVo : treeVoList) {
            addChildList(nodeTreeVo, nodeMap);
        }

        log.info("\n\n\n平铺后到第一层节点map为：{}", JSONUtil.toJsonStr(nodeMap));

        nodeMap.forEach((aLong, list) -> {
            log.info("id 为：{}, 总数为: {}", aLong, list.size());
        });

    }

    private List<NodeVo> addChildList(NodeTreeVo child, HashMap<Long, List<NodeVo>> nodeMap) {
        NodeVo nodeVo = BeanUtil.toBean(child, NodeVo.class);
        List<NodeVo> list = Lists.newArrayList(nodeVo);
        // 如果是叶子节点 直接设置
        List<NodeTreeVo> childList = child.getChildList();
        if (CollectionUtil.isNotEmpty(childList)) {
            list = childList.stream().flatMap(nodeTreeVo -> {
                return addChildList(nodeTreeVo, nodeMap).stream();
            }).collect(Collectors.toList());
        }

        nodeMap.put(nodeVo.getId(), list);
        return list;
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
