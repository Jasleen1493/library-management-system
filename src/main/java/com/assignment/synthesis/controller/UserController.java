package com.assignment.synthesis.controller;

import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.exception.UserNotFoundException;
import com.assignment.synthesis.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiOperation(value = "userController", notes = "Operations pertaining to user", produces = "application/json")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user")
	@ApiOperation(value = "Return user")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
	}
	
	@PostMapping("/users")
	@ApiOperation(value = "Return list of users")
	public ResponseEntity<?> add(@Valid @RequestBody List<User> users) {
		return new ResponseEntity<>(userService.saveUsers(users), HttpStatus.OK);
	}
	
	@GetMapping("/users")
	@ApiOperation(value = "Return list of users")
	public ResponseEntity<?> view() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}")
	@ApiOperation(value = "Delete user")
	public ResponseEntity<?> delete(@PathVariable Long userId) throws UserNotFoundException {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	
}
	
	
