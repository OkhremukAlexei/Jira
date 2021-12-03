package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.pojo.dto.ProjectDto;
import com.jira.repos.ProjectRepo;
import com.jira.services.ProjectService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepo projectRepo ;

    @Autowired
    private TeamDetailsServiceImpl teamService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    @Transactional
    public List<ProjectDto> getProjectsList(){
        List<Project> projects = projectRepo.findAll();
        teamService.countNumOfUsers(projects);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (Project project: projects) {
            projectDtoList.add(ProjectDto.build(project));
        }

        return projectDtoList;
    }

    /*
    @Override
    @Transactional
    public ProjectDto getProjectByUserId(long userId){
        Project project = projectRepo.findProjectByTeam_Users_IdIs(userId)
                .orElseThrow(() -> new ServiceException("Project Not Found with user id: " + userId));
        teamService.countNumOfUsers(project);

        return ProjectDto.build(project);
    }
    */

    @Override
    @Transactional
    public List<ProjectDto> getProjectsByUserId(Long userId){
        List<Project> projects = projectRepo.findProjectsByTeam_Users_IdIs(userId);
        teamService.countNumOfUsers(projects);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project project: projects){
            projectDtoList.add(ProjectDto.build(project));
        }

        return projectDtoList;
    }

    @Override
    @Transactional
    public ProjectDto getOne(Long id){
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        teamService.countNumOfUsers(project);

        return ProjectDto.build(project);
    }

    @Override
    @Transactional
    public ProjectDto addProject(ProjectDto projectRequest){
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setLinkToGit(projectRequest.getLinkToGit());
        project.setProgress(0);  //TODO count progress

        project.setTeam(teamService.getNewTeam(userService.getCurrentUser()));

        projectRepo.save(project);

        return ProjectDto.build(project);
    }

    @Override
    @Transactional
    public ProjectDto updateProject(Long id, ProjectDto projectRequest){
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        project.setName(projectRequest.getName());
        project.setLinkToGit(projectRequest.getLinkToGit());

        projectRepo.save(project);

        return ProjectDto.build(project);
    }

 /*   @Transactional
    public ProjectDto addPeople(ProjectDto projectRequest){
        return ProjectDto.build(project);
    }*/

    @Override
    @Transactional
    public void delete( Project project) {
        projectRepo.delete(project);
    }

}
