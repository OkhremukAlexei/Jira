package com.jira.repos;

import com.jira.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends CrudRepository<Team,Integer> {
    @Query("select count (t) from Team t left join t.users where t.id=:id")
    int countByTeam_Id(@Param("id") long id);

    Team findById(long teamId);
}
