package com.jira.services.impl;

import com.jira.models.Task;
import com.jira.repos.TaskRepo;
import com.jira.services.TaskService;
import org.springframework.stereotype.Service;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public Iterable<Task> getAll() {
        return taskRepo.findAll();
    }

    @Override
    public Task getOne(Task task) {
        return taskRepo.findById(task.getId()).get();
    }

    @Override
    public Task put(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepo.delete(task);
    }
}
