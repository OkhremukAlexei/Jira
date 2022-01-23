package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("TeamDetailsServiceImpl")
public class TeamDetailsServiceImpl implements TeamService {
    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createNewTeam(User user){
        Team team = new Team();
        List<User> users = new ArrayList<>();

        users.add(user);

        team.setNumberOfPersons(1);
        team.setUsers(users);

        teamRepo.save(team);
    }

    @Override
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
    public int countNumOfUsers(Project project){
        long teamId = project.getTeam().getId();
        int numOfUsers = teamRepo.countByTeam_Id(teamId);
        Team team = teamRepo.findById(teamId);
        team.setNumberOfPersons(numOfUsers);
        teamRepo.save(team);

        return numOfUsers;
    }

    @Override
    public void countNumOfUsers(List<Project> projects){
        for (Project project: projects) {
            countNumOfUsers(project);
        }
    }

    @Override
    public Iterable<Team> getAll() {
        return teamRepo.findAll();
    }

    @Override
    public Team getOne(Team team) {
        return teamRepo.findById(team.getId());
    }

    @Override
    public Team put( Team team) {
        return teamRepo.save(team);
    }

    @Override
    public void delete( Team team) {
        teamRepo.delete(team);
    }

    @Override
    public Team setNewUsersInTeam(long teamId, List<User> users) {
        Team team = teamRepo.findById(teamId);
        team.setUsers(users);
        return teamRepo.save(team);
    }

    @Override
    public void deleteUsersInTeam(long teamId, long userId) {
        User user = userRepo.findById(userId).get();
        Team team = teamRepo.findById(teamId);
        team.getUsers().remove(user);
        teamRepo.save(team);
    }

    public boolean existsById(long id){
        return teamRepo.existsById(id);
    }

}
