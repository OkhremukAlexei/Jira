package com.jira.pojo.dto;

import com.jira.models.User;

public class PartialUserDto {
    private Long id;

    private String login;
    private String password;
    private String roles;

    public PartialUserDto(Long id, String login, String password, String roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public static PartialUserDto build(User user){

        return new PartialUserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
