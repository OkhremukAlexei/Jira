package com.jira.controllers;

import com.jira.models.Project;
import com.jira.pojo.JwtResponse;
import com.jira.pojo.MessageResponse;
import com.jira.repos.ProjectRepo;
import com.jira.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectService projectService;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping("/projectList")
    @CrossOrigin
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(projectService.getProjectsList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Project getOne(@PathVariable("id") Project project) {
        return project;
    }

    @GetMapping("/usersProject/{id}")
    @CrossOrigin
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('ADMIN')") //TODO should admin see users project by his id?
    public ResponseEntity<?> getOneByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(projectService.getProjectByUserId(id));
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public Project put(@RequestBody Project project) {
        return projectRepo.save(project);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Project project) {
        projectRepo.delete(project);
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody Project project) {
        projectRepo.save(project);
        return ResponseEntity.ok(new MessageResponse("project ADDED"));
    }

    
}
