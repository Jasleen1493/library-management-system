package com.assignment.synthesis.service;

import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> saveBooks(List<Book> books) {
		return bookRepository.saveAll(books);
	}
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	public List<Book> viewBooks() {
		return bookRepository.findAll();
	}
	
}
