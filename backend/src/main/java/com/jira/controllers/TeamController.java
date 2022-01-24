package com.jira.controllers;

import com.jira.models.Team;
import com.jira.pojo.MessageResponse;
import com.jira.repos.TeamRepo;
import com.jira.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {

    private final TeamRepo teamRepo;

    public TeamController(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @Autowired
    @Qualifier("TeamDetailsServiceImpl")
    private TeamService teamDetailsService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Iterable<Team>> getAll() {
        final Iterable<Team> teams = teamDetailsService.getAll();
        return teams != null// &&  !teams.isEmpty()   //&&&&
                ? new ResponseEntity<>(teams, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> getOne(@PathVariable("id") Team team) {
        return team != null
                ? new ResponseEntity<>(team, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Team> put(@RequestBody Team team) {
        final Team update = teamDetailsService.put(team);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Team team) {
        teamDetailsService.delete(team);
    }

    @DeleteMapping("/team/{teamId}/people/{userId}")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePersonInTeam(@PathVariable("teamId") long teamId, @PathVariable("userId") long userId){
        if (teamDetailsService.existsById(teamId)) {
            teamDetailsService.deleteUsersInTeam(teamId, userId);
            return ResponseEntity.ok(new MessageResponse("User deleted"));
        }
        else
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: team with this id is not exist "));
    }
}
