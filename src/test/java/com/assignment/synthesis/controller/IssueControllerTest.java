package com.assignment.synthesis.controller;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.Issue;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.service.IssueService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IssueControllerTest {
	
	@MockBean
	private IssueService issueService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	public void testSaveIssue() throws Exception {
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		
		when(issueService.saveIssue(issue)).thenReturn(Constants.ISSUE_SAVED_MESSAGE);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/issue")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.content(mapper.writeValueAsString(issue))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(Constants.ISSUE_SAVED_MESSAGE, result.getResponse().getContentAsString());
		
	}
	
	
	
	@Test
	public void testSaveIssues() throws Exception {
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		List<Issue> issueList = new ArrayList<>();
		issueList.add(issue);
		
		when(issueService.saveIssues(issueList)).thenReturn(Constants.ISSUE_SAVED_MESSAGE);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/issues")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.content(mapper.writeValueAsString(issueList))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		
		assertEquals(Constants.ISSUE_SAVED_MESSAGE, result.getResponse().getContentAsString());
		
	}
	
	
	@Test
	public void testViewIssue() throws Exception {
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		List<Issue> issueList = new ArrayList<>();
		issueList.add(issue);
		
		when(issueService.findIssueByUser(1L)).thenReturn(issueList);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/issues/1")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<Issue> issues = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Issue>>() {});
		
		assertEquals(issueList.get(0).getUser().getUserId(), issues.get(0).getUser().getUserId());
		
	}
	
	@Test
	public void testReturnBookFromUser() throws Exception {
		Issue issue = new Issue();
		issue.setIssueId(1);
		User user = new User();
		user.setUserId(1);
		user.setName("Jasleen");
		
		Book book = new Book();
		book.setBookId(2);
		book.setName("Java");
		
		issue.setBook(book);
		issue.setUser(user);
		
		when(issueService.returnBooksFromUser(Arrays.asList(book),1L)).thenReturn(Constants.RETURN_BOOKS_MESSAGE+1);
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/return/books/1")
				.with(user(Constants.ADMIN).password(Constants.ADMIN_PASSWORD).roles(Constants.ROLE_ADMIN))
				.content(mapper.writeValueAsString(Arrays.asList(book)))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(Constants.RETURN_BOOKS_MESSAGE+1, result.getResponse().getContentAsString());
		
	}
	
}
