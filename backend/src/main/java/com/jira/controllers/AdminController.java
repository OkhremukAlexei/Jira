package com.jira.controllers;

import com.jira.pojo.MessageResponse;
import com.jira.repos.AccountRepo;
import com.jira.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @GetMapping("/userlist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(accountRepo.findAll());
    }

    @PostMapping("/userinfo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@RequestParam Long id) {
        if (userRepo.existsById(id)) {
            return ResponseEntity.ok(userRepo.findById(id));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: User with this id is not exist "));
    }
}
