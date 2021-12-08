package com.jira.controllers;

import com.jira.models.Account;
import com.jira.pojo.MessageResponse;
import com.jira.repos.AccountRepo;
import com.jira.repos.RoleRepo;
import com.jira.repos.UserRepo;
import com.jira.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/userlist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @PostMapping("/userinfo/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUser(@RequestParam Long id) {
        if (userRepo.existsById(id)) {
            return ResponseEntity.ok(userRepo.findById(id));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: User with this id is not exist "));
    }

    @PostMapping("/setmanager/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> setManager(@RequestParam Long id) {
        if (userRepo.existsById(id)){
            adminService.setManager();
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