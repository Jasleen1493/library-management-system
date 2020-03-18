package com.assignment.synthesis.entity;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@ApiOperation(value = "issue", notes = "Contains information about mapping of users and books")
@Data
public class Issue implements Serializable {
	
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "Primary key of Issue.", name = "issueId")
	private long issueId;
	
	@ManyToOne( cascade = CascadeType.REMOVE)
	private Book book;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private User user;
	
	@ApiModelProperty(notes = "Date of Issue.", name = "issueDate")
	private Date issueDate;
	
	@ApiModelProperty(notes = "Date of Return.", name = "returnDate")
	private Date returnDate;
	
}