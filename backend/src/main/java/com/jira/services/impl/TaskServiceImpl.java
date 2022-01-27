package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.Status;
import com.jira.models.Task;
import com.jira.models.User;
import com.jira.pojo.dto.TaskDto;
import com.jira.repos.ProjectRepo;
import com.jira.repos.TaskRepo;
import com.jira.repos.UserRepo;
import com.jira.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {

    private static final Logger LOGGER= LoggerFactory.getLogger(TaskServiceImpl.class);


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
        LOGGER.info("TaskServiceImpl method getAll");
        List<Task> tasks = taskRepo.findAll();
        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
    }

    @Override
    public TaskDto getOne(Integer id) {
        LOGGER.info("TaskServiceImpl method getOne "+id);
        Task task = taskRepo.findById(id).get();
        return TaskDto.build(task);
    }

    @Override
    public Task put(Task task) {
        LOGGER.info("TaskServiceImpl method put "+task.getTitle()+" "+task.getTitle()+" "+task.getDescription()+" id "+task.getId());
        return taskRepo.save(task);
    }

    @Override
    public void delete(Task task) {
        LOGGER.info("TaskServiceImpl method delete "+task.getTitle()+" "+task.getTitle()+" "+task.getDescription()+" id "+task.getId());
        taskRepo.delete(task);
    }

    @Override
    public void addTask(TaskDto taskDto) {
        LOGGER.info("TaskServiceImpl method addTask "+taskDto.getTitle()+" "+taskDto.getTitle()+" "+taskDto.getDescription()+" id "+taskDto.getId());
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
        LOGGER.info("TaskServiceImpl method addTask "+taskDto.getTitle()+" "+taskDto.getTitle()+" "+taskDto.getDescription()+" id ");
        Task task = taskRepo.getById(id);
        task.setSpentTime(new SimpleDateFormat("HH:mm").parse(taskDto.getSpentTime()));
        task.setDateTime(LocalDateTime.now());
        task.setStatus(Status.ASSIGNED);

        taskRepo.save(task);

        return TaskDto.build(task);
    }

    @Override
    public List<TaskDto> getUsersTasks(Long projectId, Long userId) {
        LOGGER.info("TaskServiceImpl method getUsersTasks projectId "+projectId+" userId "+userId);
        List<Task> tasks = taskRepo.findByProject_IdAndUsers_Id(projectId, userId);

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
    }

    @Override
    public void completeTask(int id) {
        LOGGER.info("TaskServiceImpl method completeTask "+id);
        Task task = taskRepo.getById(id);

        task.setStatus(Status.COMPLETED);

        taskRepo.save(task);
    }

    @Override
    public void closeTask(int id) {
        LOGGER.info("TaskServiceImpl method closeTask "+id);
        Task task = taskRepo.getById(id);

        task.setStatus(Status.CLOSED);

        taskRepo.save(task);
    }

    @Override
    public List<TaskDto> getProjectTasks(Long id) {
        LOGGER.info("TaskServiceImpl method getProjectTasks " + id);

        List<Task> tasks = taskRepo.findByProject_Id(id);

        List<TaskDto> taskDtoList = new ArrayList<>();

        for (Task task: tasks) {
            taskDtoList.add(TaskDto.build(task));
        }

        return taskDtoList;
    }

    @Override
    public int countProgress(Long id) {
        LOGGER.info("TaskServiceImpl method countProgress " + id);
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
