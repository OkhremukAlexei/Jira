package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.models.ERole;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("UsersServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Override
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getOne(User us) {
        return userRepo.findById(us.getId()).get();
    }

    @Override
    public User put(User us) {
        return userRepo.save(us);
    }

    @Override
    public void delete(User us) {
        userRepo.delete(us);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findBySpecificRoles(ERole.ROLE_USER);
    }

    @Override
    public List<User> getUsersOutsideTheProject(long id){
        List<User> allUsersList = userRepo.findBySpecificRoles(ERole.ROLE_USER);
        List<User> usersInTeamList = userRepo.findByTeams_Project_Id(id);
        allUsersList.removeAll(usersInTeamList);

        return allUsersList;
    }


}
