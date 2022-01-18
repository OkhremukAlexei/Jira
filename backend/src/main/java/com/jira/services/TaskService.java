package com.jira.services;

import com.jira.models.Task;
import com.jira.pojo.dto.TaskDto;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getOne(Integer id);

    TaskDto put(Integer id, TaskDto task);

    void delete(Integer id);

    void addTask(TaskDto taskDto);

    TaskDto startTask(int id, TaskDto taskDto) throws ParseException;

    List<TaskDto> getUsersTasks(Long projectId, Long userId);

    void completeTask(int id);

    void closeTask(int id);

    List<TaskDto> getProjectTasks(Long id);

    int countProgress(Long id);
}
