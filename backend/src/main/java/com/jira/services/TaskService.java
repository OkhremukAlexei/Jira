package com.jira.services;

import com.jira.models.Task;
import com.jira.pojo.dto.TaskDto;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    List<Task> getAll();

    Task getOne(Integer id);

    Task put(Integer id, Task task);

    void delete(Integer id);

    void addTask(Task task);

    Task startTask(int id, Task task) throws ParseException;

    List<Task> getUsersTasks(Long projectId, Long userId);

    List<Task> getAllUsersTasks(Long userId);

    void completeTask(int id);

    void closeTask(int id);

    List<Task> getProjectTasks(Long id);

    int countProgress(Long id);
}
