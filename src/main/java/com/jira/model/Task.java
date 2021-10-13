package com.jira.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "spent_time", nullable = false)
    private int spentTime;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "task_id")
    private List<Comment> comments;

    public Task(String title, String description, Timestamp startTime, int spentTime, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.spentTime = spentTime;
        this.comments = comments;
    }

    public Task(String title, String description, Timestamp startTime, int spentTime) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.spentTime = spentTime;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (spentTime != task.spentTime) return false;
        if (!id.equals(task.id)) return false;
        if (!title.equals(task.title)) return false;
        if (!description.equals(task.description)) return false;
        if (!startTime.equals(task.startTime)) return false;
        return comments.equals(task.comments);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + spentTime;
        result = 31 * result + comments.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", spentTime=" + spentTime +
                ", comments=" + comments +
                '}';
    }
}
