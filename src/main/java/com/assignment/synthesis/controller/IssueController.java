package com.assignment.synthesis.controller;


import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.Issue;
import com.assignment.synthesis.service.IssueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@ApiOperation(value = "issueController", notes = "Operations pertaining to issue", produces = "application/json")
public class IssueController {
	
	@Autowired
	IssueService issueService;
	
	@PostMapping("/issue")
	@ApiOperation(value = "Issue books to users")
	public ResponseEntity<?> issue(@Valid @RequestBody Issue issue) {
		return new ResponseEntity<>(issueService.saveIssue(issue), HttpStatus.OK);
	}
	
	@PostMapping("/issues")
	@ApiOperation(value = "Issue multiple books to multiple users")
	public ResponseEntity<?> issues(@Valid @RequestBody List<Issue> issues) {
		return new ResponseEntity<>(issueService.saveIssues(issues), HttpStatus.OK);
	}

	@GetMapping("/issues/{userId}")
	@ApiOperation(value = "view issued books to user")
	public List<Issue> view(@PathVariable Long userId){
		return issueService.findIssueByUser(userId);
	}

    @PostMapping("/return/books/{userId}")
    @ApiOperation(value = "return books from user")
    public ResponseEntity<?> returnBooksFromUser(@PathVariable Long userId, @RequestBody List<Book> books) {
        return new ResponseEntity<>(issueService.returnBooksFromUser(books,userId), HttpStatus.OK);
    }

}
