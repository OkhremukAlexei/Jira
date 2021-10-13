package com.jira.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "linktogit", nullable = false)
    private String linkToGit;

    @Column(name = "progress", nullable = false)
    private int progress;

    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "status", joinColumns = @JoinColumn(name = "status_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> status;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_task",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Project(String projectName, String linkToGit, int progress, Set<Status> status, Set<Task> tasks) {
        this.projectName = projectName;
        this.linkToGit = linkToGit;
        this.progress = progress;
        this.status = status;
        this.tasks = tasks;
    }

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (progress != project.progress) return false;
        if (!id.equals(project.id)) return false;
        if (!projectName.equals(project.projectName)) return false;
        if (!linkToGit.equals(project.linkToGit)) return false;
        if (!status.equals(project.status)) return false;
        return tasks.equals(project.tasks);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + projectName.hashCode();
        result = 31 * result + linkToGit.hashCode();
        result = 31 * result + progress;
        result = 31 * result + status.hashCode();
        result = 31 * result + tasks.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", linkToGit='" + linkToGit + '\'' +
                ", progress=" + progress +
                ", status=" + status +
                ", tasks=" + tasks +
                '}';
    }
}
