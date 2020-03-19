package com.assignment.synthesis.controller;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setName("Jasleen");
		
		when(userService.addUser(any(User.class))).thenReturn(user);
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
		List<User> userList = new ArrayList<>();
		userList.add(user);
		when(userService.saveUsers(any(List.class))).thenReturn(userList);
		
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
		when(userService.getUsers()).thenReturn(userList);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<User> users = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<User>>() {});
		
		assertEquals(userList.get(0).getName(), users.get(0).getName());
		
	}
	
	@Test
	public void testDeleteUser() throws Exception {
		User user = new User();
		user.setName("Jasleen");
		
		when(userService.deleteUser(1L)).thenReturn(Constants.DELETE_USER_MESSAGE);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(Constants.DELETE_USER_MESSAGE, result.getResponse().getContentAsString());
		
	}
}
