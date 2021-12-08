package com.jira.services;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;

import java.util.List;

public interface TeamService {
    void createNewTeam(User user);
    Team getNewTeam(User user);
    void countNumOfUsers();
    void countNumOfUsers(Project project);
    void countNumOfUsers(List<Project> projects);

    Iterable<Team> getAll();
    Team getOne(Team team);
    Team put( Team team);
    void delete( Team team);
    boolean existsById(long id);

    Team setNewUsersInTeam(long id, List<User> users);
    void deleteUsersInTeam(long teamId, long userId);
}
