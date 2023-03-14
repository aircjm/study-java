package com.aircjm.study.mybatis.mapper;

import com.aircjm.study.mybatis.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SysUserMapper extends BaseMapper<SysUser> {


    SysUser selectOne(Long id);


    void updateOne(SysUser ucSysUser);
}
