package com.jira.controllers;

import com.jira.models.Project;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.dto.ProjectDto;
import com.jira.repos.ProjectRepo;
import com.jira.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getOneByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getProjectsByUserId(id));
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

    @PutMapping("/people")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addPeople(@RequestBody ProjectDto project) {
        if (projectService.existsById(project.getId())) {
            projectService.addPeopleToProject(project);
            return ResponseEntity.ok(new MessageResponse("User added"));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: team with this id is not exist "));
    }

    @DeleteMapping("/project/{projectId}/user/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePersonInTeam(@PathVariable("projectId") long projectId, @PathVariable("userId") long userId){
        if (projectService.existsById(projectId)) {
            projectService.deleteUsersInTeam(projectId, userId);
            return ResponseEntity.ok(new MessageResponse("User deleted"));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: team with this id is not exist "));
    }

}