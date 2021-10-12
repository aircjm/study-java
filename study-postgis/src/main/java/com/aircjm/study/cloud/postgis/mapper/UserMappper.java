package com.aircjm.study.cloud.postgis.mapper;

import com.aircjm.study.cloud.postgis.domain.StudyGps;
import com.aircjm.study.cloud.postgis.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * user 操作dao类
 * @author aircjm
 */
@Repository
public interface UserMappper extends BaseMapper<User> {
}
