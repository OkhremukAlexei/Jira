package com.jira;


import com.jira.models.Account;
import com.jira.models.Project;
import com.jira.models.Team;
import com.jira.pojo.LoginRequest;
import com.jira.pojo.SignupRequest;
import com.jira.repos.ProjectRepo;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.impl.*;
import com.jira.validator.LoginRequestValidator;
import com.jira.validator.SignUpRequestValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Component
class JiraApplicationTests {

	private static final Logger LOGGER= LoggerFactory.getLogger(JiraApplicationTests.class);


	@Autowired
	LoginRequestValidator loginRequestValidator;

	@Autowired
	SignUpRequestValidator signUpRequestValidator;


	private static final String REGEX="1v";
	private static final String EMAIL_REGEX="tsiklon2001@gmai.com";
	private static final String NAME_REGEX="Timofey";
	private static final String SURNAME_REGEX="Kononovich";
	private static final boolean flag=false;

	@Test
	void TestOfLoginRequestValidator(){
		LOGGER.info("Starting of the TestOfLoginRequestValidator ");
		LoginRequest loginRequest=new LoginRequest();
		BindingResult bindingResult=new BeanPropertyBindingResult(loginRequest, "loginRequest");
		loginRequest.setLogin(REGEX);
		loginRequest.setPassword(REGEX);
		loginRequestValidator.validate(loginRequest,bindingResult);
		assertEquals(flag,bindingResult.hasErrors());
	}

	@Test
	void TestOfSignUpRequestValidator(){
		LOGGER.info("Starting of the TestOfSignUpRequestValidator ");
		SignupRequest signupRequest=new SignupRequest();
		BindingResult bindingResult=new BeanPropertyBindingResult(signupRequest, "signupRequest");
		signupRequest.setLogin(REGEX);
		signupRequest.setPassword(REGEX);
		signupRequest.setEmail(EMAIL_REGEX);
		signupRequest.setName(NAME_REGEX);
		signupRequest.setSurname(SURNAME_REGEX);
		signUpRequestValidator.validate(signupRequest,bindingResult);
		assertEquals(flag,bindingResult.hasErrors());
	}


	@Autowired
	AdminServiceImpl adminService;

	private static final int USERS=8;

	@Test
	void TestAdminServiceImplMethodFindAll(){
		LOGGER.info("Starting of the TestAdminServiceImplMethodFindAll ");
		assertEquals(USERS,adminService.findAll().size());
	}

	@Autowired
	ProjectServiceImpl projectService;

	private static final int PROJECTS=4;

	@Test
	void TestProjectServiceImplMethodGetProjectsList(){
		LOGGER.info("Starting of the TestProjectServiceImplMethodGetProjectsList ");
			assertEquals(PROJECTS,projectService.getProjectsList().size());
	}

	private static final int USER_ID=8;
	private static final int USER_PROJECTS=2;

	@Test
	void TestProjectServiceImplMethodGetProjectsByUserId(){
		LOGGER.info("Starting of the TestProjectServiceImplMethodGetProjectsByUserId ");
		assertEquals(USER_PROJECTS, projectService.getProjectsByUserId((long)USER_ID).size());
	}

	private static final int PROJECT_ID=2;
	private static final int PROJECT_MEMBERS=4;

	@Test
	void TestProjectServiceImplMethodGetOne(){
		LOGGER.info("Starting of the TestProjectServiceImplMethodGetOne ");
		assertEquals(PROJECT_MEMBERS, projectService.getOne((long)PROJECT_ID).getUsers().size());
	}

	@Autowired
	AccountServiceImpl accountService;

	private static final int ACCOUNT_ID=1;
	private static final String ACCOUNT_EMAIL="1111@gmail.com";

	@Test
	void TestAccountServiceImplMethodGetOne(){
		LOGGER.info("Starting of the TestAccountServiceImplMethodGetOne ");
		Account account=new Account();
		Account account1=new Account();
		account1.setId((long)ACCOUNT_ID);
		account.setEmail(ACCOUNT_EMAIL);
		assertEquals(account.getEmail(),accountService.getOne(account1).getEmail());
	}

	@Autowired
	TaskServiceImpl taskService;

	private static final int TASK=9;

	@Test
	void TestTaskServiceImplMethodGetAll(){
		LOGGER.info("Starting of the TestTaskServiceImplMethodGetAll ");
		assertEquals(TASK,taskService.getAll().size());
	}

	private static final int TASK_ID=2;
	private static final String TASK_TITLE="Application1";

	@Test
	void TestTaskServiceImplMethodGetOne(){
		LOGGER.info("Starting of the TestTaskServiceImplMethodGetOne ");
		assertEquals(TASK_TITLE,taskService.getOne(TASK_ID).getTitle());
	}

	private static final int PROJECT_TASK_ID=2;
	private static final int USER_TASK_ID=8;
	private static final int USER_TASKS=1;

	@Test
	void TestTaskServiceImplMethodGetUsersTasks(){
		LOGGER.info("Starting of the TestTaskServiceImplMethodGetUsersTasks ");
		assertEquals(USER_TASKS,taskService.getUsersTasks((long)PROJECT_TASK_ID,(long)USER_TASK_ID).size());
	}

	private static final int TASKS_OF_PROJECT=4;

	@Test
	void TestTaskServiceImplMethodGetProjectTasks(){
		LOGGER.info("Starting of the TestTaskServiceImplMethodGetProjectTasks ");
		assertEquals(TASKS_OF_PROJECT,taskService.getProjectTasks((long)PROJECT_TASK_ID).size());
	}

	private static final int PROJECT_PROGRESS=25;

	@Test
	void TestTaskServiceImplMethodCountProgress(){
		LOGGER.info("Starting of the TestTaskServiceImplMethodCountProgress ");
		assertEquals(PROJECT_PROGRESS,taskService.countProgress((long)PROJECT_TASK_ID));
	}

	@Autowired
	TeamDetailsServiceImpl teamDetailsService;

	private static final int TEAM_AMOUNT=4;
	private static final int TEAM_ID=2;

	@Test
	void TestTeamDetailsServiceImplMethodGetOne(){
		LOGGER.info("Starting of the TestTeamDetailsServiceImplMethodGetOne ");
		Team team = new Team();
		team.setId(TEAM_ID);
		assertEquals(TEAM_AMOUNT,teamDetailsService.getOne(team).getUsers().size());
	}

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	private static final String USER_LOGIN="1";
	private static final String USER_PASSWORD="1111";

	@Test
	void TestUserDetailsServiceImplMethodLoadUserByUsername(){
		LOGGER.info("Starting of the TestUserDetailsServiceImplMethodLoadUserByUsername ");
		assertEquals(USER_PASSWORD,userDetailsService.loadUserByUsername(USER_LOGIN).getPassword());
	}

	@Autowired
	UserServiceImpl userService;

	private static final int NUMBER_OF_USERS=4;

	@Test
	void TestUserServiceImplMethodGetAllUsers(){
		LOGGER.info("Starting of the TestUserServiceImplMethodGetAllUsers ");
		assertEquals(NUMBER_OF_USERS,userService.getAllUsers().size());
	}

}
