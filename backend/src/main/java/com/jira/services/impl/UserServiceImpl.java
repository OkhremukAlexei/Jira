package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.models.ERole;
import com.jira.models.Team;
import com.jira.models.User;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("UsersServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<User> getAll() {
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
    public List<User> getUsers() {
        return userRepo.findBySpecificRoles(ERole.ROLE_USER);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user: userRepo.findBySpecificRoles(ERole.ROLE_USER)) {
            userDtoList.add(UserDto.build(user));
        }
        return userDtoList;
    }

    @Override
    public List<UserDto> getUsersOutsideTheProject(long id){
        List<User> allUsersList = userRepo.findBySpecificRoles(ERole.ROLE_USER);
        List<User> usersInTeamList = userRepo.findByTeams_Project_Id(id);
        allUsersList.removeAll(usersInTeamList);

        List<UserDto> userDtoList = new ArrayList<>();

        for (User user: allUsersList) {
            userDtoList.add(UserDto.build(user));
        }

        return userDtoList;
    }

    public User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        return user;
    }

    public UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRoles(user.getRole());
        return userDto;
    }
}
