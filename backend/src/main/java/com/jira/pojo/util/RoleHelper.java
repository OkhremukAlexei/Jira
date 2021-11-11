package com.jira.pojo.util;

import com.jira.models.ERole;
import com.jira.models.User;

import java.util.List;

public abstract class RoleHelper {
    public static User findManagerInList(List<User> users){
        for (User user: users) {
            if(user.getRoles().iterator().next().getName().equals(ERole.ROLE_MANAGER)){
                return user;
            }
        }
        return null;
    }
}
