package com.jira.services.impl;

import com.jira.models.Project;
import com.jira.models.User;
import com.jira.pojo.dto.ProjectDto;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.ProjectRepo;
import com.jira.repos.UserRepo;
import com.jira.services.ProjectService;
import com.jira.services.TaskService;
import com.jira.services.TeamService;
import com.jira.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("ProjectServiceImpl")
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ProjectServiceImpl.class);


    @Autowired
    private ProjectRepo projectRepo ;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService uService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public List<Project> getProjectsList(){
        LOGGER.info("ProjectServiceImpl method getProjectsList");
        List<Project> projects = projectRepo.findAll();

        for (Project project: projects) {
            project.setProgress(taskService.countProgress(project.getId()));
            project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));
        }

        return projects;
    }

    @Override
    @Transactional
    public List<Project> getProjectsByUserId(Long userId){
        List<Project> projects = projectRepo.findProjectsByTeam_Users_IdIs(userId);
        LOGGER.info("ProjectServiceImpl method getProjectsByUserId "+userId);

        for (Project project: projects) {
            project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));
        }

        return projects;
    }

    @Override
    @Transactional
    public Project getOne(Long id){
        LOGGER.info("ProjectServiceImpl method getOne "+id);

        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        project.getTeam().setNumberOfPersons(teamService.countNumOfUsers(project));

        return project;
    }

    @Override
    @Transactional
    public Project addProject(Project project){
        LOGGER.info("ProjectServiceImpl method addProject "+projectRequest.getLinkToGit()+" "+projectRequest.getName()+" "+projectRequest.getId()+" Manager's id "+projectRequest.getManager().getId()+" members "+projectRequest.getUsers().size()+" progress"+projectRequest.getProgress());

        project.setProgress(0);

        project.setTeam(teamService.getNewTeam(userService.getCurrentUser()));

        projectRepo.save(project);

        return project;
    }

    @Override
    @Transactional
    public Project updateProject(Long id, Project project){
        LOGGER.info("ProjectServiceImpl method updateProject project's id "+id+" "+projectRequest.getLinkToGit()+" "+projectRequest.getName()+" "+projectRequest.getId()+" Manager's id "+projectRequest.getManager().getId()+" members "+projectRequest.getUsers().size()+" progress"+projectRequest.getProgress());

        Project projectFromDB = projectRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + id));
        projectFromDB.setName(project.getName());
        projectFromDB.setLinkToGit(project.getLinkToGit());

        projectRepo.save(projectFromDB);

        return projectFromDB;
    }

    @Override
    @Transactional
    public void addPeopleToProject(Project projectRequest){
        LOGGER.info("ProjectServiceImpl method addProject "+projectRequest.getLinkToGit()+" "+projectRequest.getName()+" "+projectRequest.getId()+" Manager's id "+projectRequest.getManager().getId()+" members "+projectRequest.getUsers().size()+" progress"+projectRequest.getProgress());

        Project projectFromDB = projectRepo.findById(projectRequest.getId())
                .orElseThrow(() -> new ServiceException("Project Not Found with id: " + projectRequest.getId()));

        projectFromDB.setTeam(teamService.setNewUsersInTeam(projectFromDB.getTeam().getId(), projectRequest.getTeam().getUsers()));
        projectFromDB.getTeam().setNumberOfPersons(teamService.countNumOfUsers(projectFromDB));

        projectRepo.save(projectFromDB);

    }

    @Override
    public boolean existsById(long projectId) {
        LOGGER.info("ProjectServiceImpl method existsById "+ projectId);
        return projectRepo.existsById(projectId);
    }

    @Override
    public void deleteUsersInTeam(long projectId, long userId) {
        LOGGER.info("ProjectServiceImpl method existsById "+ projectId+" userId "+userId);
        long teamId = projectRepo.findById(projectId).get().getTeam().getId();
        teamService.deleteUsersInTeam(teamId, userId);
    }

    @Override
    @Transactional
    public void delete(Project project) {
        LOGGER.info("ProjectServiceImpl method addProject "+project.getLinkToGit()+" "+project.getName()+" "+project.getId()+" progress"+project.getProgress());
        projectRepo.delete(project);
    }

    @Override
    public ProjectDto convertToDto(Project project) {
        ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);

        projectDto.setNumOfPersonsInTeam(project.getTeam().getNumberOfPersons());
        projectDto.setUsers(project.getTeam().getUsers().stream().map(uService::convertToDto).collect(Collectors.toList()));
        projectDto.setManager(uService.convertToDto(RoleHelper.findManager(project.getTeam().getUsers())));

        return projectDto;
    }

    @Override
    public Project convertToEntity(ProjectDto projectDto) {
        Project project = modelMapper.map(projectDto, Project.class);

        if (projectDto.getId() != null) {
            project.setTeam(teamService.setTeam(projectDto.getId(), projectDto.getUsers()
                    .stream().map(uService::convertToEntity).collect(Collectors.toList())));
        }
        // project.setTeam(teamService.setTeam(projectDto.getId(), projectDto.getUsers()));

        return project;
    }

}
