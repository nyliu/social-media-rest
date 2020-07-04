package com.in28minutes.rest.webservices.socialmediarest.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	/*@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id){
		User user=service.findOne(id);
		if(user==null) throw new UserNotFoundException("id-" +id);
		return user;
	}*/
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id){
		Optional<User> user=userRepository.findById(id);
		if(!user.isPresent()) throw new UserNotFoundException("id-"+id);
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/users")  //input-details of user
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		//output: created status-201; and created URI or location
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id){
		Optional<User> user=userRepository.findById(id);
		
		if(!user.isPresent()) throw new UserNotFoundException("id-"+id);
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")  
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent()) throw new UserNotFoundException("id-"+id);
		
		User user = userOptional.get();
		
		post.setUser(user);  //very important
		postRepository.save(post);
		
		//output: created status-201; and created URI or location
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	

}
