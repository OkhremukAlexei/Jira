package com.jira.pojo;


import com.jira.controllers.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class JwtResponse {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthController.class);

    private String token;
    private String type = "Bearer";
    private Long id;
    private String login;
    private List<String> roles;

    public JwtResponse(String token, Long id, String login, List<String> roles) {
        this.token = token;
        this.id = id;
        this.login = login;
        this.roles = roles;
        LOGGER.info("Creating the Jwt Response: token ", token, " id",id," login ",login, " roles ",roles);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
