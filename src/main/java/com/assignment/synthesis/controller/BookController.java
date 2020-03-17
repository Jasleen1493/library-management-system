package com.assignment.synthesis.controller;

import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiOperation(value = "bookController", notes = "Operations pertaining to books", produces = "application/json")
public class BookController{
	
	@Autowired
	private BookService bookService;
	
	
	@PostMapping("/books")
	@ApiOperation(value = "Save book ")
	public ResponseEntity<?> save(@RequestBody List<Book> books) {
		return new ResponseEntity<>(bookService.addBooks(books), HttpStatus.OK);
	}
	
	@PostMapping("/book")
	@ApiOperation(value = "Save list of books ")
	public ResponseEntity<?> save(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
	}
	
	@GetMapping("/books")
	@ApiOperation(value = "Return list of books ")
	public ResponseEntity<?> view() {
		return new ResponseEntity<>(bookService.viewBooks(), HttpStatus.OK);
	}


}
