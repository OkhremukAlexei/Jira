package com.jira.pojo.dto;

import com.jira.models.Project;
import com.jira.models.Task;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.pojo.util.RoleHelper;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProjectDto {
    private Long id;
    private String name;
    private String linkToGit;
    private int progress;
    private Set<Task> tasks;
    private int numOfPersonsInTeam;
    private List<User> users;
    private User manager;

    public ProjectDto() {
    }

    public ProjectDto(String name, String linkToGit, int progress, int numOfPersonsInTeam, User manager) {
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.numOfPersonsInTeam = numOfPersonsInTeam;
        this.manager = manager;
    }

    public ProjectDto(Long id, String name, String linkToGit, int progress, int numOfPersonsInTeam, List<User> users, User manager) {
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

        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getLinkToGit(),
                project.getProgress(),
                project.getTeam().getNumberOfPersons(),
                project.getTeam().getUsers(),
                manager
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

    public List<User> getUsers() {
        return users;
    }

    public User getManager() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return progress == that.progress && numOfPersonsInTeam == that.numOfPersonsInTeam && id.equals(that.id) && name.equals(that.name) && Objects.equals(linkToGit, that.linkToGit) && Objects.equals(tasks, that.tasks) && Objects.equals(users, that.users) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, linkToGit, progress, tasks, numOfPersonsInTeam, users, manager);
    }
}
