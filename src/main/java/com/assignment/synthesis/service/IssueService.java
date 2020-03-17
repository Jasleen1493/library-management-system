package com.assignment.synthesis.service;

import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.Issue;
import com.assignment.synthesis.entity.User;
import com.assignment.synthesis.exception.UserNotFoundException;
import com.assignment.synthesis.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IssueService {
	
	@Autowired
	IssueRepository issueRepository;

    @Autowired
    UserService userService;

	@Transactional
	public String saveIssue(Issue issue) {
		if (issue.getIssueDate() == null) issue.setIssueDate(new Date());
		issueRepository.save(issue);
		return "Issue saved succesfully";
	}
	
	public String saveIssues(List<Issue> issues) {
		issues.stream().filter(issue -> issue.getIssueDate() == null).forEach(issue -> issue.setIssueDate(new Date()));
		issueRepository.saveAll(issues);
        return "Issues saved succesfully";
	}

    public List<Issue> findByUser(Long userId){
	    User user = userService.getUser(userId);
	    return issueRepository.findByUserAndReturnDateIsNull(user);
    }

    public String returnBooksFromUser(List<Book> books,Long userId) {
        User user = userService.getUser(userId);
        List<Issue> issues = books.stream().map(book -> issueRepository.findByUserAndBook(user,book)).collect(Collectors.toList());
	    issues.stream().forEach(issue -> issue.setReturnDate(new Date()));
        return "Books returned by user id " + userId;
    }
}
