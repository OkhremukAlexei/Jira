package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.repos.ProjectRepo;
import com.jira.repos.UserRepo;
import com.jira.services.ProjectService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Project> getProjectsList(){
        List<Project> projects = projectRepo.findAll();

        for (Project project: projects) {
            project.setProgress(taskService.countProgress(project.getId()));
            project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));
        }

        return projects;
    }

    @Override
    @Transactional
    public List<Project> getProjectsByUserId(Long userId){
        List<Project> projects = projectRepo.findProjectsByTeam_Users_IdIs(userId);

        for (Project project: projects) {
            project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));
        }

        return projects;
    }

    @Override
    @Transactional
    public Project getOne(Long id){
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));

        return project;
    }

    @Override
    @Transactional
    public Project addProject(Project project){
        project.setProgress(0);

        project.setTeam(teamService.getNewTeam(userService.getCurrentUser()));

        projectRepo.save(project);

        return project;
    }

    @Override
    @Transactional
    public Project updateProject(Long id, Project project){
        Project projectFromDB = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        projectFromDB.setName(project.getName());
        projectFromDB.setLinkToGit(project.getLinkToGit());

        projectRepo.save(projectFromDB);

        return projectFromDB;
    }

    @Override
    @Transactional
    public void addPeopleToProject(Project projectRequest){
        Project projectFromDB = projectRepo.findById(projectRequest.getId())
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + projectRequest.getId()));

        projectFromDB.setTeam(teamService.setNewUsersInTeam(projectFromDB.getTeam().getId(), projectRequest.getTeam().getUsers()));
        projectFromDB.getTeam().setNumberOfPersons(teamService.countNumOfUsers(projectFromDB));

        projectRepo.save(projectFromDB);

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
