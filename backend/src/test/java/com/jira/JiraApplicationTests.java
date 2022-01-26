package com.jira;


import com.jira.pojo.LoginRequest;
import com.jira.pojo.SignupRequest;
import com.jira.repos.TeamRepo;
import com.jira.repos.UserRepo;
import com.jira.services.impl.AdminServiceImpl;
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

	@Autowired
	LoginRequestValidator loginRequestValidator;

	@Autowired
	SignUpRequestValidator signUpRequestValidator;

	@Autowired
	AdminServiceImpl adminService;

	private static final String REGEX="1v";
	private static final String EMAIL_REGEX="tsiklon2001@gmai.com";
	private static final String NAME_REGEX="Timofey";
	private static final String SURNAME_REGEX="Kononovich";
	private static final int USERS=8;
	private static final boolean flag=false;
	private static final Logger LOGGER= LoggerFactory.getLogger(JiraApplicationTests.class);

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


	@Test
	void TestAdminServiceImplMethodFindAll(){
		LOGGER.info("Starting of the TestAdminServiceImplMethodFindAll ");
		assertEquals(USERS,adminService.findAll().size());
	}
}
