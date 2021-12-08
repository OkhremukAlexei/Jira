package com.jira.pojo.dto;

import com.jira.models.User;

public class AccountDto {
    private Long id;

    private String name;
    private String surname;
    private String email;
    private UserDto user;

    public AccountDto(Long id, String name, String surname, String email, UserDto user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.user = user;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}