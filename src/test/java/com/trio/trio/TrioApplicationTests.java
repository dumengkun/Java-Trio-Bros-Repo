package com.trio.trio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TrioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrioRunner runner;

	@MockBean
	private TrioClerk clerk;

	@Test
	public void homePageTest() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Welcome to Java Trio Bros Learner App!")));
	
	}

	@Test
	public void userStory1_gameTest() throws Exception {

		mockMvc.perform(get("/maze/maze")).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void userStory4_runnerTest() throws Exception {

		String validCode = "class Trio { public static void main(String[] args) { System.out.println(\"Hello!\"); } }";

		when(runner.compileAndRunJavaCode(validCode)).thenReturn("Hello!");

	}

	@Test
	public void userStory5_quizTest() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Question 1")));

		mockMvc.perform(get("/ifelse")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("x is greater than y")));

	}

	@Test
	public void userStory7_clerkTest() throws Exception {

		Volunteer user = new Volunteer("test@gmail.com", "Test");

		// Save a user
		clerk.setUser(user);
		verify(clerk, times(1)).setUser(user);

		// Find a user by email
		clerk.findByUserEmail(user.getEmail());
		verify(clerk, times(1)).findByUserEmail(user.getEmail());

		// Delete a user by email
		clerk.deleteByUserEmail(user.getEmail());
		verify(clerk, times(1)).deleteByUserEmail(user.getEmail());

	}

	@Test
	public void userStory7_signinPageTest() throws Exception {

		mockMvc.perform(get("/signin")).andDo(print()).andExpect(status().isOk())
		.andExpect(model().attribute("guest", new Guest()));

	}

}
