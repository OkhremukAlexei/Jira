package com.jira.controllers;


import com.jira.models.User;
import com.jira.repos.UserRepo;
import com.jira.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    private UserDetailsServiceImpl userService;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User put(@RequestBody User user) {
        return userRepo.save(user);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }

    @GetMapping("/roleUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
}
