package com.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.beans.Organization;
import com.company.beans.User;
import com.company.dao.OrganizationRepo;
import com.company.dao.UserRepository;
import com.company.exceptions.CompanyException;
import com.company.exceptions.UserNotFoundException;

@RestController
public class OrganizationController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	OrganizationRepo orgRepo;
	
	@GetMapping("/db/users/{id}/orgs")
	public List<Organization> getOrganizationOfUser(@PathVariable int id)
	{
		User user = userRepo.getOne(id);
		return user.getOrganizations();
	}
	
	@PostMapping("/db/users/{id}/orgs")
	public Organization createOrgOfUser(@PathVariable int id, @RequestBody Organization organization)
	{
		User  user = userRepo.getOne(id);
		if(user==null)
			throw new UserNotFoundException("id : "+id);
		organization.setUser(user);
		Organization org = orgRepo.save(organization);
		return org;
	}
}
