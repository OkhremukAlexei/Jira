package com.jira.pojo.util;

import com.jira.models.ERole;
import com.jira.models.User;

import java.util.ArrayList;
import java.util.List;

public abstract class RoleHelper {
    private static List<User> findManagersInList(List<User> users){
        List<User> managers = new ArrayList<>();
        for (User user: users) {
            if(user.getRoles().iterator().next().getName().equals(ERole.ROLE_MANAGER)){
                managers.add(user);
            }
        }
        return managers;
    }

    public static User findManager(List<User> users){
        return findManagersInList(users).get(0);
    }
}
