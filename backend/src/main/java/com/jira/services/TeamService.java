package com.jira.services;

import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.pojo.dto.UserDto;

import java.util.List;

public interface TeamService {
    void createNewTeam(User user);
    Team getNewTeam(User user);

    int countNumOfUsers(Project project);
    void countNumOfUsers(List<Project> projects);

    Team findByProjectId(Long id);
    Iterable<Team> getAll();
    Team getOne(Team team);
    Team put( Team team);
    void delete( Team team);
    boolean existsById(long id);

    Team setNewUsersInTeam(long id, List<User> users);
    void deleteUsersInTeam(long teamId, long userId);

    Team setTeam(Long id, List<UserDto> users);
}
