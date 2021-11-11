package com.jira.services;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TeamDetailsServiceImpl {
    @Autowired
    private TeamRepo teamRepo;

    public Team createNewTeam(User user){
        Team team = new Team();

        List<User> users = new ArrayList<>();
        users.add(user);

        team.setNumberOfPersons(1);
        team.setUsers(users);

        return team;
    }

    public void countNumOfUsers(){
        Iterable<Team> teamList = teamRepo.findAll();
        for (Team team: teamList) {
            int numOfUsers = teamRepo.countByTeam_Id(team.getId());
            team.setNumberOfPersons(numOfUsers);
            teamRepo.save(team);
        }
    }

    public void countNumOfUsers(Project project){
        long teamId = project.getTeam().getId();
        int numOfUsers = teamRepo.countByTeam_Id(teamId);
        Team team = teamRepo.findById(teamId);
        team.setNumberOfPersons(numOfUsers);
        teamRepo.save(team);
    }

    public void countNumOfUsers(List<Project> projects){
        for (Project project: projects) {
            countNumOfUsers(project);
        }
    }

}
