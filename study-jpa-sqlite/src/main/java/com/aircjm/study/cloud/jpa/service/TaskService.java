package com.aircjm.study.cloud.jpa.service;

import com.aircjm.study.cloud.jpa.dao.TaskRepository;
import com.aircjm.study.cloud.jpa.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

}
