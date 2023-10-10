package com.aircjm.study.mybatisplus.mapper;

import com.aircjm.study.mybatisplus.domain.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {


    SysUser selectOne(@Param("id") Long id);


    void updateOne(SysUser ucSysUser);
}
