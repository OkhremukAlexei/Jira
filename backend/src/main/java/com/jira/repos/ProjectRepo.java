package com.jira.repos;

import com.jira.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long> {
    List<Project> findProjectsByTeam_Users_IdIs(Long id);

    Optional<Project> findProjectByTeam_Users_IdIs(Long id);
    Optional<Project> findProjectById(Long id);
    Optional<Project> findByTeam_Users_IdIs(Long id);

}
