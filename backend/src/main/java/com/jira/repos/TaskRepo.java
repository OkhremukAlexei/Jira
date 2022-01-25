package com.jira.repos;

import com.jira.models.Task;
import com.jira.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    @Query("select count(t) from Task t where t.project.id=:id")
    int countAmountTasksInProject(@Param("id") long projectId);

    @Query("select count(t) from Task t where t.project.id=:id and t.status='CLOSED'")
    int countAmountClosedTasksInProject(@Param("id") long projectId);

    @Query("select t from Task t where t.users in :id and (t.status='ASSIGNED' or t.status='DISCUSSION')")
    List<Task> findAllActiveTasksByUserId (@Param("id") long id);

    List<Task> findByProject_IdAndUsers_Id(Long id, Long id1);

    List<Task> findByProject_Id(Long id);

    Task getById(int id);
}
