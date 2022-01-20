package com.jira.controllers;


import com.jira.models.User;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    @Qualifier("UsersServiceImpl")
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User put(@RequestBody User user) {
        return userService.put(user);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") User user) {
        userService.delete(user);
    }

    @GetMapping("/roleUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getUsersOutsideTheProject(@PathVariable("id") Long projectId){
        return ResponseEntity.ok(userService.getUsersOutsideTheProject(projectId));
    }
}


