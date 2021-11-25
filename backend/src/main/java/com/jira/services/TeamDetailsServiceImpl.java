package com.jira.services;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("TeamDetailsServiceImpl")
public class TeamDetailsServiceImpl implements TeamService{
    @Autowired
    private TeamRepo teamRepo;

    @Override
    public Team createNewTeam(User user){
        Team team = new Team();
        List<User> users = new ArrayList<>();

        users.add(user);

        team.setNumberOfPersons(1);
        team.setUsers(users);

        teamRepo.save(team);
    }

    @Transactional
    public Team getNewTeam(User user){
        Team team = new Team();
        List<User> users = new ArrayList<>();

        users.add(user);

        team.setNumberOfPersons(1);
        team.setUsers(users);

        teamRepo.save(team);

        return teamRepo.findFirstByOrderByIdDesc();
    }

    @Override
    public void countNumOfUsers(){
        Iterable<Team> teamList = teamRepo.findAll();
        for (Team team: teamList) {
            int numOfUsers = teamRepo.countByTeam_Id(team.getId());
            team.setNumberOfPersons(numOfUsers);
            teamRepo.save(team);
        }
    }

    @Override
    public void countNumOfUsers(Project project){
        long teamId = project.getTeam().getId();
        int numOfUsers = teamRepo.countByTeam_Id(teamId);
        Team team = teamRepo.findById(teamId);
        team.setNumberOfPersons(numOfUsers);
        teamRepo.save(team);
    }

    @Override
    public void countNumOfUsers(List<Project> projects){
        for (Project project: projects) {
            countNumOfUsers(project);
        }
    }

    public Iterable<Team> getAll() {
        return teamRepo.findAll();
    }


    public Team getOne(Team team) {
        return teamRepo.findById(team.getId());
    }


    public Team put( Team team) {
        return teamRepo.save(team);
    }


    public void delete( Team team) {
        teamRepo.delete(team);
    }

}
