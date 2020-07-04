package com.in28minutes.rest.webservices.socialmediarest.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	/*@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user=service.findOne(id);
		if(user==null) throw new UserNotFoundException("id-" +id);
		return user;
	}*/
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id){
		User user=service.findOne(id);
		if(user==null) throw new UserNotFoundException("id-"+id);
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User user=service.deleteById(id);
		if(user==null) throw new UserNotFoundException("id-" +id);
	}
	
	@PostMapping("/users")  //input-details of user
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		//output: created status-201; and created URI or location
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	

}
