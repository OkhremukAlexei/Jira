package com.jira.repos;

import com.jira.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project,Long> {

}
