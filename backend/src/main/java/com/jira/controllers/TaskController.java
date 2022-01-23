package com.jira.controllers;

import com.jira.models.Project;
import com.jira.models.Task;
import com.jira.models.User;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.dto.ProjectDto;
import com.jira.pojo.dto.TaskDto;
import com.jira.pojo.dto.UserDto;
import com.jira.pojo.util.RoleHelper;
import com.jira.repos.TaskRepo;
import com.jira.services.impl.TaskServiceImpl;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(convertToDto(taskService.getOne(id)));
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getByProjectId(@PathVariable("id") Long id){
        List<Task> tasks = taskService.getProjectTasks(id);
        return ResponseEntity.ok(tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/project/{projectId}/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getByUserId(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId){
        List<Task> tasks = taskService.getUsersTasks(projectId, userId);
        return ResponseEntity.ok(tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> add(@RequestBody TaskDto taskDto) throws ParseException {
        taskService.addTask(convertToEntity(taskDto));
        return ResponseEntity.ok(new MessageResponse("Task added"));
    }

    @PutMapping("{id}/start")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> startTask(@PathVariable("id") int id, @RequestBody TaskDto taskDto) throws ParseException {
        return ResponseEntity.ok(taskService.startTask(id, convertToEntity(taskDto)));
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
    public TaskDto put(@PathVariable("id") Integer id, @RequestBody TaskDto taskDto) throws ParseException {
        return convertToDto(taskService.put(id, convertToEntity(taskDto)));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        taskService.delete(id);
    }

    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);

        taskDto.setProject(convertToDto(task.getProject()));
        taskDto.setUsers(task.getUsers().stream().map(this::convertToDto).collect(Collectors.toList()));
  //      taskDto.setDateTime(task.getDateTime());
  //      taskDto.setSpentTime(task.getSpentTime());

        return taskDto;
    }

    private  Task convertToEntity(TaskDto taskDto) throws ParseException {
        Task task = modelMapper.map(taskDto, Task.class);

//        task.setDateTime(taskDto.getDateTimeConverted());
 //       task.setSpentTime(taskDto.getSpentTimeConverted());

        return task;
    }//TODO check add task

    private ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

        projectDto.setNumOfPersonsInTeam(project.getTeam().getNumberOfPersons());
        projectDto.setUsers(project.getTeam().getUsers().stream().map(this::convertToDto).collect(Collectors.toList()));
        projectDto.setManager(convertToDto(RoleHelper.findManager(project.getTeam().getUsers())));

        return projectDto;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRoles(user.getRole());
        return userDto;
    }

}
