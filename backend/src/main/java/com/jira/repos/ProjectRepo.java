package com.jira.repos;

import com.jira.models.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project,Integer> {
}
