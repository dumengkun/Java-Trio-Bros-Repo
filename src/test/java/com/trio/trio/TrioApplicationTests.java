package com.trio.trio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TrioRunner runner;

	@Test
	public void homePageTest() throws Exception {

		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Welcome to Java Trio Bros Learner App!")));
	
	}

	@Test
	public void userStory4_runnerTest() throws Exception {

		String testCode = "class Trio { public static void main(String[] args) { System.out.println(\"Hello!\"); } }";

		when(runner.compileAndRunJavaCode(testCode)).thenReturn("Hello!");
		
	}

}
