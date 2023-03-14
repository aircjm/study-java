package com.aircjm.study.cloud.jpa.model;

import javax.persistence.*;

@Entity
@Table(name = "sub_task")
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subtaskTitle;

    // 记录 Subtask 与 Task 之间的关系
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    // 省略getter和setter方法
}