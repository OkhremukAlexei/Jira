package com.jira.controllers;


import com.jira.models.User;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    @Qualifier("UsersServiceImpl")
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAll() {
        List<User> users =  userService.getAllUsers();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users.stream().map(userService::convertToDto).collect(Collectors.toList()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getOne(@PathVariable("id") User user) {
        return user != null
                ? new ResponseEntity<>(userService.convertToDto(user), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> put(@RequestBody UserDto user) {
        User update = userService.put(userService.convertToEntity(user));
        return new ResponseEntity<>(userService.convertToDto(update),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") User user) {
        userService.delete(user);
    }

    @GetMapping("/roleUser")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<List<UserDto>> getUsers(){
        final List <User> users = userService.getUsers();
        return users!=null && !users.isEmpty()
                ? new ResponseEntity<>(users.stream().map(userService::convertToDto).collect(Collectors.toList()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<List<UserDto>> getUsersOutsideTheProject(@PathVariable("id") Long projectId){
        List <User> users = userService.getUsersOutsideTheProject(projectId);
        return ResponseEntity.ok(users.stream().map(userService::convertToDto).collect(Collectors.toList()));
    }
}


