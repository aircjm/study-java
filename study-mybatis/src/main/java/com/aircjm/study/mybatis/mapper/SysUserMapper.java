package com.aircjm.study.mybatis.mapper;

import com.aircjm.study.mybatis.domain.SysUser;

public interface SysUserMapper {


    SysUser selectOne(Long id);


    void updateOne(SysUser ucSysUser);
}
