package com.jira.services;

import com.jira.models.Project;
import com.jira.repos.ProjectRepo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo ;

    @Autowired
    private TeamDetailsServiceImpl teamService;

    @Transactional
    public List<Project> getProjectsList(){
        List<Project> projects = projectRepo.findAll();
        teamService.countNumOfUsers(projects);

        return projects;
    }

    @Transactional
    public Project getProjectByUserId(long userId){
        teamService.countNumOfUsers();

        return projectRepo.findProjectByTeam_Users_IdIs(userId)
                .orElseThrow(() -> new ServiceException("Project Not Found with user id: " + userId));
    }


}
