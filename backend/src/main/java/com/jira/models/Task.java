package com.jira.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
public class  Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String description;
    @Column(name="dateTime")
    private LocalDateTime dateTime;
    @Column(name="spentTime")
    private int spentTime;

    @ManyToMany(mappedBy = "tasks")
    private Set<Project> projects;


    public Task() {
    }

    public Task(int id, String title, String description, LocalDateTime dateTime, int spentTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.spentTime = spentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && spentTime == task.spentTime && title.equals(task.title) && description.equals(task.description) && dateTime.equals(task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dateTime, spentTime);
    }

    /* @OneToMany   ?
    private Comment comment;*/
}
