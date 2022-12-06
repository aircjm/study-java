package com.aircjm.study.cloud.postgis.mapper;

import com.aircjm.study.cloud.postgis.domain.Tasks;

public interface TasksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tasks record);

    int insertSelective(Tasks record);

    Tasks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tasks record);

    int updateByPrimaryKey(Tasks record);
}