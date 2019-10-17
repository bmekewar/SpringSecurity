package com.bvm.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.security.model.User;
import com.bvm.security.repository.UserRepository;

@RestController
public class Controller {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String get() {
		return "Welcome";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured")
	public String secured() {
		return "Secured Login";
	}

	@GetMapping("/principal")
	public Principal retrievePrincipal(Principal principal) {
		return principal;
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		String password = user.getPassword();
		String encryptPassword = encoder.encode(password);
		user.setPassword(encryptPassword);
		userRepository.save(user);
		return "User [" + user.getUsername() + "] added successfully";
	}
}
