package com.jira.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="linkToGit")
    private String linkToGit;
    @Column(name="progress")
    private int progress;

    public Project() {
    }

    public Project(int id, String name, String linkToGit, int progress) {
        this.id = id;
        this.name = name;
        this.linkToGit = linkToGit;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && progress == project.progress && name.equals(project.name) && linkToGit.equals(project.linkToGit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, linkToGit, progress);
    }


    //    private Status status; ?
//    private Team team; ?
}
