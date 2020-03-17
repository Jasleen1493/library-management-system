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
@ApiOperation(value = "user", notes = "Contains information about users of library")
@Data
public class User {
	
	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "Primary key of User.", name = "userId")
	private long userId;
	
	@ApiModelProperty(notes = "Name of user.", name = "name")
	private String name;
	
	@ApiModelProperty(notes = "Role of user.", name = "role")
	private String role;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
	private List<Issue> issues;

}