package com.jira;

import com.jira.repos.TeamRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JiraApplicationTests {

	@Autowired
	TeamRepo teamRepo;

	@Test
	void contextLoads() {
		int num = teamRepo.countByTeam_Id(1);
		int actual = 2;
		assertEquals(actual, num);
	}

}
