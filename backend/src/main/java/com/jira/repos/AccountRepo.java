package com.jira.repos;

import com.jira.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends JpaRepository<Account,Long> {
    Boolean existsByEmail(String email);
}
