package com.chetan.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Post {
	@GeneratedValue
	@Id
	private Integer id;
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	

	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public User getUser() {
		return user;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
