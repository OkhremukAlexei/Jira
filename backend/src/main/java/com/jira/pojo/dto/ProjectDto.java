package com.jira.pojo.dto;

import com.jira.models.Project;
import com.jira.models.Task;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.pojo.util.RoleHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String linkToGit;
    private int progress;
//    private Set<Task> tasks;
    private int numOfPersonsInTeam;
    private List<PartialUserDto> users;
    private PartialUserDto manager;

    public ProjectDto() {
    }

    public ProjectDto(String name, String linkToGit, int progress, int numOfPersonsInTeam, PartialUserDto manager) {
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.numOfPersonsInTeam = numOfPersonsInTeam;
        this.manager = manager;
    }

    public ProjectDto(Long id, String name, String linkToGit, int progress, int numOfPersonsInTeam, List<PartialUserDto> users, PartialUserDto manager) {
        this.id = id;
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.numOfPersonsInTeam = numOfPersonsInTeam;
        this.users = users;
        this.manager = manager;
    }


    public static ProjectDto build(Project project){
        User manager = RoleHelper.findManagerInList(project.getTeam().getUsers());
        PartialUserDto managerDto = null;

        if (manager != null) {
            managerDto = PartialUserDto.build(manager);
        }
        List<PartialUserDto> userDtoList = new ArrayList<>();

        for (User user: project.getTeam().getUsers()) {
            userDtoList.add(PartialUserDto.build(user));
        }

        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getLinkToGit(),
                project.getProgress(),
                project.getTeam().getNumberOfPersons(),
                userDtoList,
                managerDto
        );
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

    public int getNumOfPersonsInTeam() {
        return numOfPersonsInTeam;
    }

    public List<PartialUserDto> getUsers() {
        return users;
    }

    public PartialUserDto getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "name='" + name + '\'' +
                ", linkToGit='" + linkToGit + '\'' +
                ", progress=" + progress +
                ", numOfPersonsInTeam=" + numOfPersonsInTeam +
                ", manager=" + manager +
                '}';
    }
}
