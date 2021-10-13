package com.jira.controllers;

import com.jira.models.Project;
import com.jira.repos.ProjectRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    private final ProjectRepo projectRepo;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping
    public Iterable<Project> getAll() {
        return projectRepo.findAll();
    }

    @GetMapping("{id}")
    public Project getOne(@PathVariable("id") Project project) {
        return project;
    }

    @PutMapping
    public Project put(@RequestBody Project project) {
        return projectRepo.save(project);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Project project) {
        projectRepo.delete(project);
    }
}
