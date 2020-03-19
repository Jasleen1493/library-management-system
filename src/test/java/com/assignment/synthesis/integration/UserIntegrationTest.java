package com.assignment.synthesis.integration;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setName("Jasleen");
		user.setUserId(1);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.content(mapper.writeValueAsString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(user.getName(), mapper.readValue(result.getResponse().getContentAsString(), User.class).getName());
		
	}
	
	
	
	@Test
	public void testSaveUsers() throws Exception {
		User user = new User();
		user.setName("Jasleen");
		user.setUserId(1);
		List<User> userList = new ArrayList<>();
		userList.add(user);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.content(mapper.writeValueAsString(userList))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<User> users = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		
		assertEquals(userList.get(0).getName(), users.get(0).getName());
		
	}
	
	
	@Test
	public void testViewUsers() throws Exception {
		User user = new User();
		user.setName("Jasleen");
		List<User> userList = new ArrayList<>();
		userList.add(user);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<User> users = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		
		assertEquals(userList.get(0).getName(), users.get(0).getName());
		
	}
	
}
