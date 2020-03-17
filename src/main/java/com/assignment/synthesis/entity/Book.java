package com.assignment.synthesis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@ApiOperation(value = "book", notes = "Contains information about books in library")
@Data
public class Book {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "Primary key of Book.", name = "bookId")
	private long bookId;
	
	@ApiModelProperty(notes = "Name of book.", name = "name")
	private String name;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Issue> issues;
	
}