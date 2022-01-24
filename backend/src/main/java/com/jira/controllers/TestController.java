package com.jira.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @GetMapping("/all")
    public String allAccess() {
        return "public API";  //???
    }
 /*   public ResponseEntity<String> allAccess() {
        final String answer = "public API";
        return ResponseEntity<>(answer, HttpStatus.OK);  //???
    }*/
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('MANAGER') or hasRole('ROLE_ADMIN')")
    public String userAccess() {
        return "user API";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public String moderatorAccess() {
        return "moderator API";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminAccess() {
        return "admin API";
    }
}