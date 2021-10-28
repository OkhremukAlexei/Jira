package com.jira.services;

import com.jira.models.Project;
import com.jira.repos.ProjectRepo;
import com.jira.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo ;

    @Autowired
    TeamRepo teamRepo;

    public List<Project> getProjectsList(){
        return projectRepo.findAll();
    }

}
