package com.jira.services;

import com.jira.models.Project;
import com.jira.pojo.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectsList();
    List<Project> getProjectsByUserId(Long userId);
    Project getOne(Long id);
    Project addProject(Project project);
    Project updateProject(Long id, Project project);
    void delete(Project project);

    void addPeopleToProject(Project project);

    boolean existsById(long projectId);
    void deleteUsersInTeam(long projectId, long userId);
}
