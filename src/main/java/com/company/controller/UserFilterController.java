package com.company.controller;

import java.util.Date;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.beans.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserFilterController {

	@GetMapping("/filteredUser")
	public MappingJacksonValue retrieveUser()
	{
		User user = new User(1, "Sunil", new Date());
		return getFIlterMapping(user);
	}
	public MappingJacksonValue getFIlterMapping(User user)
	{
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("birthDate");
		FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter", filter);
		
		MappingJacksonValue mapping  = new MappingJacksonValue(user);
		mapping.setFilters(filters);
		return mapping;
	}
}
