package com.assignment.synthesis.integration;

import com.assignment.synthesis.constants.Constants;
import com.assignment.synthesis.entity.Book;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testSaveBook() throws Exception {
		Book book = new Book();
		book.setName("Java");
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/book")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.content(mapper.writeValueAsString(book))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		assertEquals(book.getName(), mapper.readValue(result.getResponse().getContentAsString(), Book.class).getName());
		
	}
	
	@Test
	public void testSaveBooks() throws Exception {
		Book book = new Book();
		book.setName("Java");
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/books")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.content(mapper.writeValueAsString(bookList))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<Book> books = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Book>>() {});
		
		assertEquals(bookList.get(0).getName(), books.get(0).getName());
		
	}
	
	
	@Test
	public void testViewBooks() throws Exception {
		Book book = new Book();
		book.setName("Java");
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/books")
				.with(user(Constants.LIBRARIAN).password(Constants.LIBRARIAN_PASSWORD).roles(Constants.ROLE_LIBRARIAN))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andReturn();
		
		List<Book> books = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Book>>() {});
		
		assertEquals(bookList.get(0).getName(), books.get(0).getName());
		
	}
	
}
