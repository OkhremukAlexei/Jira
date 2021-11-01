package com.jira.controllers;

import com.jira.models.Team;
import com.jira.repos.TeamRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

    private final TeamRepo teamRepo;

    public TeamController(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public Iterable<Team> getAll() {
        return teamRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public Team getOne(@PathVariable("id") Team team) {
        return team;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public Team put(@RequestBody Team team) {
        return teamRepo.save(team);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Team team) {
        teamRepo.delete(team);
    }
}
