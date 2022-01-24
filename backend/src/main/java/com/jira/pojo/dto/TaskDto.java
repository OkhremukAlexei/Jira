package com.jira.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jira.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat spentTimeFormat
            = new SimpleDateFormat("HH:mm");

    private int id;
    private String title;
    private String description;
    private String dateTime;
    private String spentTime;
    private ProjectDto project;
    private List<UserDto> users;
    private Status status;

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(String spentTime) {
        this.spentTime = spentTime;
    }

    public ProjectDto getProject() {
        return project;
    }

    public void setProject(ProjectDto project) {
        this.project = project;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

/*
    public Date getDateTimeConverted() throws ParseException {
        return dateFormat.parse(this.dateTime);
    }

    public void setDateTime(Date date) {
        if (date != null) {
            this.dateTime = dateFormat.format(date);
        }
    }

    public Date getSpentTimeConverted() throws ParseException {
        return spentTimeFormat.parse(this.spentTime);
    }

    public void setSpentTime(Date date) {
        if (date != null) {
            this.spentTime = spentTimeFormat.format(date);
        }

    }
*/

}
