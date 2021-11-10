package com.jira.controllers;

import com.jira.models.Task;
import com.jira.repos.TaskRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskRepo taskRepo;

    public TaskController(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping
    public Iterable<Task> getAll() {
        return taskRepo.findAll();
    }

    @GetMapping("{id}")
    public Task getOne(@PathVariable("id") Task task) {
        return task;
    }

    @PutMapping
    public Task put(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Task task) {
        taskRepo.delete(task);
    }
}
