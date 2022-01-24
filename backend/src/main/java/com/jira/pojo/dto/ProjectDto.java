package com.jira.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {
    private Long id;
    private String name;
    private String linkToGit;
    private int progress;
    private int numOfPersonsInTeam;
    private List<UserDto> users;
    private UserDto manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkToGit() {
        return linkToGit;
    }

    public void setLinkToGit(String linkToGit) {
        this.linkToGit = linkToGit;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getNumOfPersonsInTeam() {
        return numOfPersonsInTeam;
    }

    public void setNumOfPersonsInTeam(int numOfPersonsInTeam) {
        this.numOfPersonsInTeam = numOfPersonsInTeam;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public UserDto getManager() {
        return manager;
    }

    public void setManager(UserDto manager) {
        this.manager = manager;
    }
}
