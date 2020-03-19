package com.assignment.synthesis.service;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.Issue;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IssueServiceTest {
	
	@InjectMocks
	private IssueService issueService;
	
	@Mock
	private IssueRepository issueRepository;
	
	@Mock
	private UserService userService;
	
	@Test
	public void testSaveIssue(){
		Issue issue = new Issue();
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		issue.setIssueDate(null);
		
		when(issueRepository.save(any(Issue.class))).thenReturn(issue);
		assertEquals(Constants.ISSUE_SAVED_MESSAGE,issueService.saveIssue(issue));
	}
	
	@Test
	public void testSaveIssues(){
		Issue issue = new Issue();
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		issue.setIssueDate(null);
		
		List<Issue> issues = new ArrayList<>();
		issues.add(issue);
		
		when(issueRepository.saveAll(any(List.class))).thenReturn(issues);
		assertEquals(Constants.ISSUE_SAVED_MESSAGE,issueService.saveIssues(issues));
	}
	
	
	@Test
	public void testFindIssueByUser(){
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		issue.setIssueDate(null);
		issue.setBook(book);
		issue.setUser(user);
		
		List<Issue> issues = new ArrayList<>();
		issues.add(issue);
		
		when(userService.getUser(1L)).thenReturn(user);
		when(issueRepository.findByUserAndReturnDateIsNull(any(User.class))).thenReturn(issues);
		
		List<Issue> issueList = issueService.findIssueByUser(user.getUserId());
		assertEquals(issues.get(0).getIssueId(),issueList.get(0).getIssueId());
	}
	
	
	@Test
	public void testReturnBooksFromUser(){
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		issue.setIssueDate(null);
		issue.setBook(book);
		issue.setUser(user);
		
		List<Issue> issues = new ArrayList<>();
		issues.add(issue);
		
		when(userService.getUser(1L)).thenReturn(user);
		when(issueRepository.findByUserAndBook(user,book)).thenReturn(issue);
		when(issueRepository.saveAll(any(List.class))).thenReturn(issues);
		assertEquals(Constants.RETURN_BOOKS_MESSAGE+1,issueService.returnBooksFromUser(Arrays.asList(book),user.getUserId()));
		
	}
}
