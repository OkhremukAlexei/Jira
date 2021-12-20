package com.jira.pojo.dto;

import com.jira.models.Project;
import com.jira.models.Task;
import com.jira.models.User;
import com.jira.pojo.util.RoleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PartialProjectDto {
    private Long id;
    private String name;
    private String linkToGit;
    private int progress;
    private Set<Task> tasks;
    private int numOfPersonsInTeam;
    private List<UserDto> users;
    private UserDto manager;

    public PartialProjectDto(Long id, String name, String linkToGit, int progress, int numOfPersonsInTeam, List<UserDto> users, UserDto manager) {
        this.id = id;
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.numOfPersonsInTeam = numOfPersonsInTeam;
        this.users = users;
        this.manager = manager;
    }

    public static PartialProjectDto build(Project project){
        User manager = RoleHelper.findManagerInList(project.getTeam().getUsers());
        UserDto managerDto = null;

        if (manager != null) {
            managerDto = UserDto.build(manager);
        }
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user: project.getTeam().getUsers()) {
            userDtoList.add(UserDto.build(user));
        }

        return new PartialProjectDto(
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public int getNumOfPersonsInTeam() {
        return numOfPersonsInTeam;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public UserDto getManager() {
        return manager;
    }
}
