package com.company.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.company.beans.User;
import com.company.dao.UserDao;
import com.company.exceptions.UserNotFoundException;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userDao.findAll();
	}
	@GetMapping("users/{userId}")
	public User createUser(@Valid @PathVariable(name="userId") int id)
	{
		User user = userDao.findOne(id); 
		if(user == null) {
			throw new UserNotFoundException("Id : "+ id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User savedUser =  userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users")
	public void deleteUser(@PathVariable int id)
	{
		User user = userDao.deleteById(id);
		if(user == null)
		{
			throw new UserNotFoundException("id: "+id);
		}
	}
}
