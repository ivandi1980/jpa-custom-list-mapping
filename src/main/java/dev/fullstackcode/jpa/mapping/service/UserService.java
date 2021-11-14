package dev.fullstackcode.jpa.mapping.service;

import java.util.List;

import dev.fullstackcode.jpa.mapping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.fullstackcode.jpa.mapping.entity.User;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Value("${spring.datasource.url}")
	private String datasourceurl;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Integer id) {
		
		return userRepository.findById(Long.valueOf(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found with Id :"+id));
		
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	
	public List<User> getAllUsersSortByUsertype() {
		return userRepository.findAllUsersSortByUsertype();
	}

}
