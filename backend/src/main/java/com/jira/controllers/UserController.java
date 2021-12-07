package com.jira.controllers;


import com.jira.models.User;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    @Qualifier("UsersServiceImpl")
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAll() {
        final List<User> users =  userService.getAll();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getOne(@PathVariable("id") User user) {
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> put(@RequestBody User user) {
        final User update = userService.put(user);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") User user) {
        userService.delete(user);
    }

    @GetMapping("/roleUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<List<User>> getUsers(){
        final List <User> users = userService.getUsers();
        return users!=null && !users.isEmpty()
                ? new ResponseEntity<>(users,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


