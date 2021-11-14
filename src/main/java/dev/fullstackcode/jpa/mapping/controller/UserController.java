package dev.fullstackcode.jpa.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fullstackcode.jpa.mapping.entity.User;
import dev.fullstackcode.jpa.mapping.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public User saveUser(@RequestBody  User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/getSortedUsers")
	public List<User> getAllUsersSortByUsertype() {		
		return userService.getAllUsersSortByUsertype();
	}
	


}
