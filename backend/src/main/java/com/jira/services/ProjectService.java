package com.jira.services;

import com.jira.models.Project;
import com.jira.pojo.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getProjectsList();
    ProjectDto getProjectByUserId(long userId);
    ProjectDto getOne(Long id);
    ProjectDto addProject(ProjectDto projectRequest);
    ProjectDto updateProject(Long id, ProjectDto projectRequest);
    void delete( Project project);
}
