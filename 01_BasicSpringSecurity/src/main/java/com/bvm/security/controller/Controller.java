package com.bvm.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/")
	public String get() {
		return "Welcome";
	}

	@GetMapping("/user")
	public String user() {
		return "Welcome User";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}
}
