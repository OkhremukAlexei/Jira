package com.jira.controllers;

import com.jira.models.Task;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.dto.TaskDto;
import com.jira.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    @Qualifier("TaskServiceImpl")
    private TaskServiceImpl taskService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks.stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(taskService.convertToDto(taskService.getOne(id)));
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getByProjectId(@PathVariable("id") Long id){
        List<Task> tasks = taskService.getProjectTasks(id);
        return ResponseEntity.ok(tasks.stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getTasksByUserId(@PathVariable("id") Long id){
        List<Task> tasks = taskService.getAllUsersTasks(id);
        return ResponseEntity.ok(tasks.stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/project/{projectId}/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getByUserId(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId){
        List<Task> tasks = taskService.getUsersTasks(projectId, userId);
        return ResponseEntity.ok(tasks.stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> add(@RequestBody TaskDto taskDto) throws ParseException {
        taskService.addTask(taskService.convertToEntity(taskDto));
        return ResponseEntity.ok(new MessageResponse("Task added"));
    }

    @PutMapping("task/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public TaskDto put(@PathVariable("id") Integer id, @RequestBody TaskDto taskDto) throws ParseException {
        return taskService.convertToDto(taskService.put(id, taskService.convertToEntity(taskDto)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        taskService.delete(id);
    }

}
