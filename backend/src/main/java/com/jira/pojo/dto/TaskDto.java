package com.jira.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jira.models.Status;
import com.jira.models.Task;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskDto {
    private int id;
    private String title;
    private String description;
    private String dateTime;
    private String spentTime;
    private ProjectDto project;
    private UserDto user;
    private String status;

    public TaskDto() {
    }

    public TaskDto(int id, String title, String description, UserDto user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public TaskDto(int id, String title, String description, String dateTime, String spentTime, UserDto user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.spentTime = spentTime;
        this.user = user;
    }

    public TaskDto(int id, String title, String description, String dateTime, String spentTime, UserDto user, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.spentTime = spentTime;
        this.user = user;
        this.status = status;
    }

    public TaskDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static TaskDto build(Task task){
        String dateTime = null;
        String spentTime = null;
        try {
            dateTime = task.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            spentTime = new SimpleDateFormat("HH:mm").format(task.getSpentTime());
        } catch (NullPointerException e){
            System.err.println("Null pointer exception");
        }

        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                dateTime,
                spentTime,
                UserDto.build(task.getUsers().stream().findAny().get()),
                task.getStatus().toString()
        );
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(String spentTime) {
        this.spentTime = spentTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }
    public Status getStatusEnum(String status){
        switch (status){
            case "NEW": {
                return Status.NEW;
            }
            case "ASSIGNED": {
                return Status.ASSIGNED;
            }
            case "DISCUSSION": {
                return Status.DISCUSSION;
            }
            case "CANCELED": {
                return Status.CANCELED;
            }
            case "POSTPONED": {
                return Status.POSTPONED;
            }
            case "COMPLETED": {
                return Status.COMPLETED;
            }
            case "CLOSED": {
                return Status.CLOSED;
            }
        }

        return null;
    }

    public Status getStatusEnum(String status){
        switch (status){
            case "NEW": {
                return Status.NEW;
            }
            case "ASSIGNED": {
                return Status.ASSIGNED;
            }
            case "DISCUSSION": {
                return Status.DISCUSSION;
            }
            case "CANCELED": {
                return Status.CANCELED;
            }
            case "POSTPONED": {
                return Status.POSTPONED;
            }
            case "COMPLETED": {
                return Status.COMPLETED;
            }
            case "CLOSED": {
                return Status.CLOSED;
            }
        }

        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
