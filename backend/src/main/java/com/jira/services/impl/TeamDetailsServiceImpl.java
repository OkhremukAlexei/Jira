package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("TeamDetailsServiceImpl")
public class TeamDetailsServiceImpl implements TeamService {

    private static final Logger LOGGER= LoggerFactory.getLogger(TeamDetailsServiceImpl.class);


    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void createNewTeam(User user){
        LOGGER.info("TeamDetailsServiceImpl method createNewTeam "+user.getLogin()+" "+user.getPassword()+" "+user.getRoles()+" "+user.getId());
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
        LOGGER.info("TeamDetailsServiceImpl method getNewTeam "+user.getLogin()+" "+user.getPassword()+" "+user.getRoles()+" "+user.getId());

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
        LOGGER.info("TeamDetailsServiceImpl method countNumOfUsers ");

        Iterable<Team> teamList = teamRepo.findAll();
        for (Team team: teamList) {
            int numOfUsers = teamRepo.countByTeam_Id(team.getId());
            team.setNumberOfPersons(numOfUsers);
            teamRepo.save(team);
        }
    }

    @Override
    public void countNumOfUsers(Project project){
        LOGGER.info("TeamDetailsServiceImpl method countNumOfUsers "+project.getLinkToGit()+" "+project.getName()+" "+project.getId()+" progress"+project.getProgress());
        long teamId = project.getTeam().getId();
        int numOfUsers = teamRepo.countByTeam_Id(teamId);
        Team team = teamRepo.findById(teamId);
        team.setNumberOfPersons(numOfUsers);
        teamRepo.save(team);
    }

    @Override
    public void countNumOfUsers(List<Project> projects){
        LOGGER.info("TeamDetailsServiceImpl method countNumOfUsers "+ projects.size());

        for (Project project: projects) {
            countNumOfUsers(project);
        }
    }

    @Override
    public Iterable<Team> getAll() {
        LOGGER.info("TeamDetailsServiceImpl method getAll ");

        return teamRepo.findAll();
    }

    @Override
    public Team getOne(Team team) {
        LOGGER.info("TeamDetailsServiceImpl method getOne id "+team.getId()+" amount "+team.getNumberOfPersons());

        return teamRepo.findById(team.getId());
    }

    @Override
    public Team put( Team team) {
        LOGGER.info("TeamDetailsServiceImpl method put id "+team.getId()+" amount "+team.getNumberOfPersons());

        return teamRepo.save(team);
    }

    @Override
    public void delete( Team team) {
        LOGGER.info("TeamDetailsServiceImpl method delete id "+team.getId()+" amount "+team.getNumberOfPersons());

        teamRepo.delete(team);
    }

    @Override
    public Team setNewUsersInTeam(long teamId, List<User> users) {
        LOGGER.info("TeamDetailsServiceImpl method setNewUsersInTeam teamId "+teamId+" user's amount "+users.size());

        Team team = teamRepo.findById(teamId);
        team.setUsers(users);
        return teamRepo.save(team);
    }

    @Override
    public void deleteUsersInTeam(long teamId, long userId) {
        LOGGER.info("TeamDetailsServiceImpl method setNewUsersInTeam teamId "+teamId+" user's id "+userId);
        User user = userRepo.findById(userId).get();
        Team team = teamRepo.findById(teamId);
        team.getUsers().remove(user);
        teamRepo.save(team);
    }

    public boolean existsById(long id){
        LOGGER.info("TeamDetailsServiceImpl method existsById Id "+id);
        return teamRepo.existsById(id);
    }

}
