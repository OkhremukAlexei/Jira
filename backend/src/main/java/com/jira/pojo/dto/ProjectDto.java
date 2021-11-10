package com.jira.pojo.dto;

import com.jira.models.Task;
import com.jira.models.Team;
import com.jira.models.User;

import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String linkToGit;
    private int progress;
    private Set<Task> tasks;
    private int numOfPersonsInTeam;
    private Set<User> users;

    public ProjectDto(String name, String linkToGit, int progress, int numOfPersonsInTeam) {
        this.id = id;
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.tasks = tasks;
        this.numOfPersonsInTeam = numOfPersonsInTeam;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLinkToGit() {
        return linkToGit;
    }

    public int getProgress() {
        return progress;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public int getNumOfPersonsInTeam() {
        return numOfPersonsInTeam;
    }


}
