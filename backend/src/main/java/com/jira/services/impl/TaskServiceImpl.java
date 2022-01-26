package com.jira.services.impl;

import com.jira.models.Status;
import com.jira.models.Task;
import com.jira.models.User;
import com.jira.pojo.dto.TaskDto;
import com.jira.repos.ProjectRepo;
import com.jira.repos.TaskRepo;
import com.jira.services.ProjectService;
import com.jira.services.TaskService;
import com.jira.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    private final ProjectRepo projectRepo;

    private final UserService userService;

    private final ProjectService projectService;

    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepo taskRepo, ProjectRepo projectRepo, UserService userService, ProjectService projectService, ModelMapper modelMapper) {
        this.taskRepo = taskRepo;
        this.projectRepo = projectRepo;
        this.userService = userService;
        this.projectService = projectService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Task> getAll() {
        return taskRepo.findAll();
    }

    @Override
    public Task getOne(Integer id) {
        return taskRepo.findById(id).get();
    }

    @Override
    public Task put(Integer id, Task taskRequest) {
        Task task = taskRepo.getById(id);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());

        taskRepo.save(task);

        return task;
    }

    @Override
    public void delete(Integer id) {
        Task task = taskRepo.findById(id).get();

        task.getUsers().removeAll(task.getUsers());
        taskRepo.save(task);

        taskRepo.deleteById(id);
    }

    @Override
    public void addTask(Task task) {
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setStatus(Status.NEW);
        task.setProject(projectRepo.getById(task.getProject().getId()));
        task.setUsers(task.getUsers());

        taskRepo.save(task);
    }

    @Override
    public Task startTask(int id, Task taskRequest) {
        Task task = taskRepo.getById(id);

        task.setSpentTime(taskRequest.getSpentTime());
        task.setDateTime(Calendar.getInstance(TimeZone.getDefault()).getTime());
        task.setStatus(Status.ASSIGNED);

        taskRepo.save(task);

        return task;
    }

    @Override
    public List<Task> getUsersTasks(Long projectId, Long userId) {
        return taskRepo.findByProject_IdAndUsers_Id(projectId, userId);
    }

    @Override
    public List<Task> getAllUsersTasks(Long userId) {
        return taskRepo.findAllActiveTasksByUserId(userId);

    }

    @Override
    public void completeTask(int id) {
        Task task = taskRepo.getById(id);

        task.setStatus(Status.COMPLETED);

        taskRepo.save(task);
    }

    @Override
    public void closeTask(int id) {
        Task task = taskRepo.getById(id);

        task.setStatus(Status.CLOSED);

        taskRepo.save(task);
    }

    @Override
    public List<Task> getProjectTasks(Long id) {
        return taskRepo.findByProject_Id(id);
    }

    @Override
    public int countProgress(Long id) {
        int numAllTasks = taskRepo.countAmountTasksInProject(id);
        int numClosedTasks = taskRepo.countAmountClosedTasksInProject(id);

        if(numAllTasks == 0){
            return 0;
        }
        else {
            return numClosedTasks * 100 / numAllTasks;
        }
    }

    public TaskDto convertToDto(Task task) {
        TaskDto taskDto = modelMapper.map(task, TaskDto.class);

        if (!task.getUsers().isEmpty()) {
            User user = task.getUsers().iterator().next();
            taskDto.setUser(userService.convertToDto(user));
        }
        taskDto.setProject(projectService.convertToDto(task.getProject()));

        return taskDto;
    }

    public Task convertToEntity(TaskDto taskDto) {

        return modelMapper.map(taskDto, Task.class);
    }
}
