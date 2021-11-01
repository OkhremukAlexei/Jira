package com.jira.pojo;

import com.jira.Validator.ValidatorLoginAndPassword;

import java.util.Set;

@ValidatorLoginAndPassword
public class SignupRequest {
    private String login;
    private String password;
    private Set<String> roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
