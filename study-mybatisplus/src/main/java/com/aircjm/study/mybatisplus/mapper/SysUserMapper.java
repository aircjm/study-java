package com.aircjm.study.mybatisplus.mapper;

import com.aircjm.study.mybatisplus.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {


    SysUser selectOne(@Param("id") Long id);


    void updateOne(SysUser ucSysUser);
}
