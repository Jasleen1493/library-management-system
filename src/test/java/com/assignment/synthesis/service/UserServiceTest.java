package com.assignment.synthesis.service;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.exception.UserNotFoundException;
import com.assignment.synthesis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.http.client.MockClientHttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	
	@Test
	public void testSaveUser(){
		User u = new User();
		u.setName("Jasleen");
		
		when(userRepository.save(any(User.class))).thenReturn(u);
		User user = userService.addUser(u);
		assertEquals(u.getName(),user.getName());
	}
	
	@Test
	public void testSaveAllUser(){
		User u = new User();
		u.setUserId(1);
		u.setName("Jasleen");
		
		List<User> users = new ArrayList<>();
		users.add(u);
		
		when(userRepository.saveAll(any(List.class))).thenReturn(users);
		List<User> userList = userService.saveUsers(users);
		assertEquals(u.getName(),userList.get(0).getName());
	}
	
	@Test
	public void testGetAllUser(){
		User u = new User();
		u.setUserId(1);
		u.setName("Jasleen");
		
		List<User> users = new ArrayList<>();
		users.add(u);
		
		when(userRepository.findAll()).thenReturn(users);
		List<User> userList = userService.getUsers();
		assertEquals(u.getName(),userList.get(0).getName());
	}
	
	@Test
	public void testGetUser(){
		User u = new User();
		u.setUserId(1);
		u.setName("Jasleen");
		
		
		when(userRepository.findByUserId(1L)).thenReturn(u);
		User user = userService.getUser(1L);
		assertEquals(u.getName(),user.getName());
	}
	
	@Test
	public void testDeleteUser() throws UserNotFoundException {
		User u = new User();
		u.setUserId(1);
		u.setName("Jasleen");
		when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(u));
		Mockito.doNothing().when(userRepository).deleteById(1L);
		
		assertEquals(Constants.DELETE_USER_MESSAGE , userService.deleteUser(1L));
	}
	
	@Test
	public void testUserNotFoundExceptionWhileFetching() throws UserNotFoundException {
		User u = new User();
		u.setUserId(1);
		u.setName("Jasleen");
		when(userRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
		
	}
	
}
