package com.jira.repos;

import com.jira.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TaskRepo extends CrudRepository<Task,Integer> {
    @Query("select count(t) from Task t left join t.projects where t.id=:id")
    int countAmountTasksInProject(@Param("id") long projectId);
}
