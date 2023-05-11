package com.aircjm.study.cloud.jpa.controller;

import com.aircjm.study.cloud.jpa.model.Task;
import com.aircjm.study.cloud.jpa.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "TaskController")
@RestController
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    @ApiOperation("获取所有任务")
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @PostMapping("/tasks")
    @ApiOperation("添加任务")
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    @ApiOperation("删除任务")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping("/tasks/{id}")
    @ApiOperation("更新任务")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> oldTask = taskService.findById(id);
        if (oldTask.isPresent()) {
            Task newTask = oldTask.get();
            newTask.setTaskTitle(task.getTaskTitle());
            newTask.setTaskContent(task.getTaskContent());
            newTask.setItems(task.getItems());
            return taskService.save(newTask);
        } else {
            return null;
        }
    }
}
