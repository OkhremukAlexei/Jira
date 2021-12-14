package com.jira.repos;

import com.jira.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    @Query("select count(t) from Task t where t.project.id=:id")
    int countAmountTasksInProject(@Param("id") long projectId);

    @Query("select count(t) from Task t where t.project.id=:id and t.status='CLOSED'")
    int countAmountClosedTasksInProject(@Param("id") long projectId);

    List<Task> findByProject_IdAndUsers_Id(Long id, Long id1);

    List<Task> findByProject_Id(Long id);


}
