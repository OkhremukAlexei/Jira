package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.Status;
import com.jira.models.Task;
import com.jira.models.User;
import com.jira.pojo.dto.PartialProjectDto;
import com.jira.pojo.dto.TaskDto;
import com.jira.repos.ProjectRepo;
import com.jira.repos.TaskRepo;
import com.jira.repos.UserRepo;
import com.jira.services.ProjectService;
import com.jira.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<TaskDto> getAll() {
        List<Task> tasks = taskRepo.findAll();
        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
    }

    @Override
    public TaskDto getOne(Integer id) {
        Task task = taskRepo.findById(id).get();
        return TaskDto.build(task);
    }

    @Override
    public Task put(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public void delete(Task task) {
        taskRepo.delete(task);
    }

    @Override
    public void addTask(TaskDto taskDto) {
        Task task = new Task();

        Set<User> userSet = new HashSet<>();
        User user = userRepo.getById(taskDto.getUser().getId());
        userSet.add(user);

        Project project = projectRepo.getById(taskDto.getProject().getId());

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Status.NEW);
        task.setProjects(project);
        task.setUsers(userSet);

        taskRepo.save(task);
    }

    @Override
    public TaskDto startTask(int id, TaskDto taskDto) throws ParseException {
        Task task = taskRepo.getById(id);

        task.setSpentTime(new SimpleDateFormat("HH:mm").parse(taskDto.getSpentTime()));
        task.setDateTime(LocalDateTime.now());
        task.setStatus(Status.ASSIGNED);

        taskRepo.save(task);

        return TaskDto.build(task);
    }

    @Override
    public List<TaskDto> getUsersTasks(Long projectId, Long userId) {
        List<Task> tasks = taskRepo.findByProject_IdAndUsers_Id(projectId, userId);

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
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
    public List<TaskDto> getProjectTasks(Long id) {
        List<Task> tasks = taskRepo.findByProject_Id(id);

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
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
}
