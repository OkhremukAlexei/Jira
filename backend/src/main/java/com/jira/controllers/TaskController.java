package com.jira.controllers;

import com.jira.models.Task;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.dto.ProjectDto;
import com.jira.pojo.dto.TaskDto;
import com.jira.repos.TaskRepo;
import com.jira.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    @Qualifier("TaskServiceImpl")
    private TaskServiceImpl taskService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(taskService.getOne(id));
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getByProjectId(@PathVariable("id") Long id){
        return ResponseEntity.ok(taskService.getProjectTasks(id));
    }

    @GetMapping("/project/{projectId}/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getByUserId(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId){
        return ResponseEntity.ok(taskService.getUsersTasks(projectId, userId));
    }

    @GetMapping("/project/{projectId}/task/{taskId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    public ResponseEntity<?> getTaskInProject(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Integer taskId){
        return ResponseEntity.ok(new MessageResponse("work"));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public Task getOne(@PathVariable("id") Task task) {
        return task;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> add(@RequestBody TaskDto taskDto){
        taskService.addTask(taskDto);
        return ResponseEntity.ok(new MessageResponse("Task added"));
    }

    @PutMapping("{id}/start")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> startTask(@PathVariable("id") int id, @RequestBody TaskDto taskDto) throws ParseException {
        System.out.println(id + '\n' +  taskDto.toString());
        return ResponseEntity.ok(taskService.startTask(id, taskDto));
    }

    @PutMapping("{id}/complete")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> completeTask(@PathVariable("id") int id) {
        taskService.completeTask(id);
        return ResponseEntity.ok(new MessageResponse("Task completed"));
    }

    @PutMapping("{id}/close")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> closeTask(@PathVariable("id") int id) {
        taskService.closeTask(id);
        return ResponseEntity.ok(new MessageResponse("Task closed"));
    }

    @PutMapping("task/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public TaskDto put(@PathVariable("id") Integer id, @RequestBody TaskDto task) {
        return taskService.put(id, task);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        taskService.delete(id);
    }
}
