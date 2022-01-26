package com.jira.controllers;

import com.jira.models.Account;
import com.jira.models.User;
import com.jira.pojo.MessageResponse;
import com.jira.repos.AccountRepo;
import com.jira.repos.RoleRepo;
import com.jira.repos.UserRepo;
import com.jira.services.AdminService;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.jira.pojo.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    @Qualifier("AdminServiceImpl")
    AdminService adminService;

    @Autowired
    UserService userService;

    @GetMapping("/userlist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<User> users = adminService.findAll();
        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users.stream().map(userService::convertToDto).collect(Collectors.toList()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/userinfo/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        /*if (userRepo.existsById(id)) {
            return ResponseEntity.ok(userRepo.findById(id));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: User with this id is not exist "));*/
        return ResponseEntity.ok(new MessageResponse("Get User"));
    }

    @PostMapping("/manager/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> setManager(@PathVariable("id") Long id) {
        if (userRepo.existsById(id)){
            adminService.setManager(id);
            return ResponseEntity.ok(new MessageResponse("Set Manager"));
        }
        else
            return ResponseEntity.badRequest().
                body(new MessageResponse("Error: User with this id is not exist "));
    }

    @DeleteMapping("/userlist/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: User with this id is not exist "));
    }
}