package com.jira.services;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeamDetailsServiceImpl {
    @Autowired
    private TeamRepo teamRepo;

    public void countNumOfUsers(){
        Iterable<Team> teamList = teamRepo.findAll();
        for (Team team: teamList) {
            int numOfUsers = teamRepo.countByTeam_Id(team.getId());
            team.setNumberOfPersons(numOfUsers);
            teamRepo.save(team);
        }
    }

    public void countNumOfUsers(List<Project> projects){
        for (Project project: projects) {
            long teamId = project.getTeam().getId();
            int numOfUsers = teamRepo.countByTeam_Id(teamId);
            System.out.println(numOfUsers);
            Team team = teamRepo.findById(teamId);
            team.setNumberOfPersons(numOfUsers);
            teamRepo.save(team);
        }
    }

}
