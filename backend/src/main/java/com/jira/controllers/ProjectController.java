package com.jira.controllers;

import com.jira.models.Project;
import com.jira.models.User;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.dto.ProjectDto;
import com.jira.pojo.dto.UserDto;
import com.jira.pojo.util.RoleHelper;
import com.jira.repos.ProjectRepo;
import com.jira.services.ProjectService;
import com.jira.services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    @Qualifier("ProjectServiceImpl")
    private ProjectService projectService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/projectList")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAll() {
        List<Project> projects = projectService.getProjectsList();
        return ResponseEntity.ok(projects.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(convertToDto(projectService.getOne(id)));
    }

    @GetMapping("/usersProject/{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getOneByUserId(@PathVariable("id") Long id) {
        List<Project> projects = projectService.getProjectsByUserId(id);
        return ResponseEntity.ok(projects.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void delete(@PathVariable("id") Project project) {
        projectService.delete(project);
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> add(@RequestBody ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);
        return ResponseEntity.ok(convertToDto(projectService.addProject(project)));
    }

    @PutMapping("/people")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<?> addPeople(@RequestBody ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);

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

    private ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

        projectDto.setNumOfPersonsInTeam(project.getTeam().getNumberOfPersons());
        projectDto.setUsers(project.getTeam().getUsers().stream().map(this::convertToDto).collect(Collectors.toList()));
        projectDto.setManager(convertToDto(RoleHelper.findManager(project.getTeam().getUsers())));

        return projectDto;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRoles(user.getRole());
        return userDto;
    }

    private Project convertToEntity(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);

        project.setTeam(teamService.setTeam(projectDto.getId(), projectDto.getUsers()));

        return project;
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        return user;
    }
}
