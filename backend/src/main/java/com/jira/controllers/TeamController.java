package com.jira.controllers;

import com.jira.models.Team;
import com.jira.repos.TeamRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

    private final TeamRepo teamRepo;

    public TeamController(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @GetMapping
    public Iterable<Team> getAll() {
        return teamRepo.findAll();
    }

    @GetMapping("{id}")
    public Team getOne(@PathVariable("id") Team team) {
        return team;
    }

    @PutMapping
    public Team put(@RequestBody Team team) {
        return teamRepo.save(team);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Team team) {
        teamRepo.delete(team);
    }
}
