package com.jira.services;

import com.jira.models.Task;

public interface TaskService {
    Iterable<Task> getAll();

    Task getOne(Task task);

    Task put(Task task);

    void delete(Task task);
}
