package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.User;
import com.jira.pojo.dto.ProjectDto;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.ProjectRepo;
import com.jira.repos.UserRepo;
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

    @Autowired
    private TaskServiceImpl taskService;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public List<ProjectDto> getProjectsList(){
        List<Project> projects = projectRepo.findAll();
        teamService.countNumOfUsers(projects);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (Project project: projects) {
            project.setProgress(taskService.countProgress(project.getId()));
            projectDtoList.add(ProjectDto.build(project));
        }

        return projectDtoList;
    }

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
        project.setProgress(0);

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

    @Override
    @Transactional
    public void addPeopleToProject(ProjectDto projectRequest){
        Project project = projectRepo.findById(projectRequest.getId())
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + projectRequest.getId()));

        List<User> users = new ArrayList<>();
        for (UserDto userDto: projectRequest.getUsers()) {
            User user = userRepo.getById(userDto.getId());
            users.add(user);
        }

        project.setTeam(teamService.setNewUsersInTeam(project.getTeam().getId(), users));

        projectRepo.save(project);

        ProjectDto.build(project);
    }

    @Override
    public boolean existsById(long projectId) {
        return projectRepo.existsById(projectId);
    }

    @Override
    public void deleteUsersInTeam(long projectId, long userId) {
        long teamId = projectRepo.findById(projectId).get().getTeam().getId();
        teamService.deleteUsersInTeam(teamId, userId);
    }

    @Override
    @Transactional
    public void delete(Project project) {
        projectRepo.delete(project);
    }

}
