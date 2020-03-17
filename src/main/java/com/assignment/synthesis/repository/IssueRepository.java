package com.assignment.synthesis.repository;

import com.assignment.synthesis.entity.Book;
import com.assignment.synthesis.entity.Issue;
import com.assignment.synthesis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByUserAndReturnDateIsNull(User issueId);

    Issue findByUserAndBook(User user,Book book);
}
