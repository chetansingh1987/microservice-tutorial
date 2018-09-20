package com.chetan.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserRepository service;
	
	@Autowired
	private PostRepository postRepository;
	
	//retrieveAllUsers
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	//retrieveUser
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = service.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id+"Not Found");
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
									   .linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	//Delete User
	//retrieveUser
	@DeleteMapping("/jpa/users/{id}")
	public User deleteUser(@PathVariable int id) {
		User user = retrieveUser(id).getContent();
		if(user!=null)
		  service.deleteById(id);
		if(null==user) {
			throw new UserNotFoundException("Not Found");
		}
		return user;
	}
	
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location   = ServletUriComponentsBuilder
				         .fromCurrentRequest()
				         .path("/{id}")
				         .buildAndExpand(savedUser.getId())
				         .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPosts(@PathVariable int id) {
		Optional<User> user = service.findById(id);
		if(!user.isPresent()) {throw new UserNotFoundException("Not Found");}
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object>  createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = service.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id+"Not Found");
		}
		User userPojo = user.get();
		post.setUser(userPojo);
		postRepository.save(post);
		
		URI location   = ServletUriComponentsBuilder
		         .fromCurrentRequest()
		         .path("/{id}")
		         .buildAndExpand(post.getId())
		         .toUri();
		return ResponseEntity.created(location).build();
		
	}

}
