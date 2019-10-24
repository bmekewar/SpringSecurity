package com.bvm.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvm.security.JwtTokenGenerator;
import com.bvm.security.model.LoggedInUser;

@RestController
public class TokenController {

	@Autowired
	private JwtTokenGenerator tokenGenerator;

	@GetMapping("/api/hello")
	public String hello() {
		return "Hello world";
	}

	// allow /token url in security configuration so that we will be able to generate
	// token and apply to the rest of the urls
	@PostMapping("/token")
	public String generateToken(@RequestBody final LoggedInUser user) {
		return tokenGenerator.generate(user);
	}

}
