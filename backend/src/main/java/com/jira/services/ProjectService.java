package com.jira.services;

import com.jira.models.Project;
import com.jira.pojo.dto.PartialProjectDto;
import com.jira.pojo.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<PartialProjectDto> getProjectsList();
    List<PartialProjectDto> getProjectsByUserId(Long userId);
    PartialProjectDto getOne(Long id);
    ProjectDto addProject(ProjectDto projectRequest);
    ProjectDto updateProject(Long id, ProjectDto projectRequest);
    void delete( Project project);
    void addPeopleToProject(ProjectDto project);

    boolean existsById(long projectId);
    void deleteUsersInTeam(long projectId, long userId);
}
