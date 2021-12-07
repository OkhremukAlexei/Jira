package com.jira.repos;

import com.jira.models.ERole;
import com.jira.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);

    @Query( "select u from User u inner join u.roles r where r.name in :role" )
    List<User> findBySpecificRoles(@Param("role") ERole roleUser);

}
