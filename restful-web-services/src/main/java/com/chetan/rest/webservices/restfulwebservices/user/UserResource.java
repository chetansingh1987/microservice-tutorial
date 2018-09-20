package com.chetan.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chetan.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//retrieveUser
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(null==user) {
			throw new UserNotFoundException("id-"+id+"Not Found");
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
									   .linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	//Delete User
	//retrieveUser
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(null==user) {
			throw new UserNotFoundException("Not Found");
		}
		return user;
	}
	
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location   = ServletUriComponentsBuilder
				         .fromCurrentRequest()
				         .path("/{id}")
				         .buildAndExpand(savedUser.getId())
				         .toUri();
		return ResponseEntity.created(location).build();
	}
	

}
