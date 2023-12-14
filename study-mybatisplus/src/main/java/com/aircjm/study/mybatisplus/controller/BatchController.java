package com.aircjm.study.mybatisplus.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.aircjm.study.mybatisplus.domain.SysUser;
import com.aircjm.study.mybatisplus.mapper.SysUserMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@RequestMapping("batch")
@RestController
public class BatchController {


    @Resource
    private SysUserMapper sysUserMapper;



    @Resource
    DataSourceTransactionManager transactionManager;

    @Resource
    TransactionDefinition definition;

    @GetMapping("/batchInsertButEndError")
    public String batchInsertButEndError() {

        List<SysUser> list = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setAge(RandomUtil.randomInt(100));
            sysUser.setEmail("test@test.com");
            sysUser.setName("test "+ i);
            list.add(sysUser);
        }

        List<List<SysUser>> partition = Lists.partition(list, 20);

        partition.forEach(item -> {
//            TransactionStatus transaction = transactionManager.getTransaction(definition);
            try {
                item.forEach(sysUser -> {
                    sysUserMapper.insert(sysUser);
                });
//                transactionManager.commit(transaction);
            } catch (Exception e) {
//                transactionManager.rollback(transaction);
            }

        });

        return "";

    }





}
