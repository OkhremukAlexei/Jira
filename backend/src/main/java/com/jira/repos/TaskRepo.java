package com.jira.repos;

import com.jira.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task,Integer> {
}
