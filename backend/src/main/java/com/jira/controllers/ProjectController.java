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
    ProjectService projectService;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping
    @CrossOrigin
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Project> getAll() {
        return projectService.getProjectsList();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public Project getOne(@PathVariable("id") Project project) {
        return project;
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
