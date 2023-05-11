package com.aircjm.study.cloud.jpa.dao;

import com.aircjm.study.cloud.jpa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
}
