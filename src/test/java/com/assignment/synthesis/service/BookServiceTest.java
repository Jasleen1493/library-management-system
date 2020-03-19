package com.assignment.synthesis.service;

import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookRepository bookRepository;
	
	
	@Test
	public void testSaveBook(){
		Book book = new Book();
		book.setName("Java");
		
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		Book actualBook = bookService.addBook(book);
		assertEquals(book.getName(),actualBook.getName());
	}
	
	@Test
	public void testSaveBooks(){
		Book book = new Book();
		book.setName("Java");
		
		List<Book> books = new ArrayList<>();
		books.add(book);
		
		when(bookRepository.saveAll(any(List.class))).thenReturn(books);
		List<Book> bookList = bookService.addBooks(books);
		assertEquals(books.get(0).getName(),bookList.get(0).getName());
	}
	
	@Test
	public void testViewAllBook(){
		Book book = new Book();
		book.setName("Java");
		
		List<Book> books = new ArrayList<>();
		books.add(book);
		
		when(bookRepository.findAll()).thenReturn(books);
		List<Book> bookList = bookService.viewBooks();
		assertEquals(books.get(0).getName(),bookList.get(0).getName());
	}
	
	
	
}

