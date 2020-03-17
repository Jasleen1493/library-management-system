package com.assignment.synthesis.service;

import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.exception.UserNotFoundException;
import com.assignment.synthesis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> saveUsers(List<User> users) {
		return userRepository.saveAll(users);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public void deleteUser(Long userId) throws UserNotFoundException {
		Optional.of(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId))).ifPresent(
				u -> userRepository.deleteById(userId));
	}

	@Transactional
	public User getUser(Long userId) {
		return userRepository.findByUserId(userId);
	}

}
