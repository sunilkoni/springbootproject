package com.company.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.beans.User;
import com.company.dao.UserRepository;
import com.company.exceptions.UserNotFoundException;

@RestController
public class UserJpaController {

	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/db/users")
	public List<User> getUsers()
	{
		List<User> allUsers = userRepo.findAll();
		
		return allUsers;
	}
	
	@GetMapping("/db/users/{id}")
	public User getUsers(@PathVariable int id)
	{
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id : "+ id);
		return user.get();
	}
	
	@DeleteMapping("/db/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userRepo.deleteById(id);
	}
	
	@PostMapping("/db/users")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User u = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}