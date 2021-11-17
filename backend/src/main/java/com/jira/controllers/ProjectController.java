package com.jira.controllers;

import com.jira.models.Project;
import com.jira.pojo.dto.ProjectDto;
import com.jira.repos.ProjectRepo;
import com.jira.services.ProjectService;
import com.jira.services.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    @Qualifier("ProjectServiceImpl")
    private ProjectService projectService;

    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping("/projectList")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(projectService.getProjectsList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getOne(id));
    }

    @GetMapping("/usersProject/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOneByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(projectService.getProjectByUserId(id));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody ProjectDto project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Project project) {
        projectService.delete(project);
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> add(@RequestBody ProjectDto project) {
        return ResponseEntity.ok(projectService.addProject(project));
    }

    
}
