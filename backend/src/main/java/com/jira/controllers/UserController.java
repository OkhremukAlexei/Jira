package com.jira.controllers;


import com.jira.models.User;
import com.jira.repos.UserRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user) {
        return user;
    }

    @PutMapping
    public User put(@RequestBody User user) {
        return userRepo.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}
