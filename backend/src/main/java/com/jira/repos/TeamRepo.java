package com.jira.repos;

import com.jira.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepo extends CrudRepository<Team,Integer> {


}
