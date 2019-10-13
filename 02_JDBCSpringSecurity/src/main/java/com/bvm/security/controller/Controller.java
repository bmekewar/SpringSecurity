package com.bvm.security.controller;

import java.security.Principal;

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

	@GetMapping("/principal")
	public Principal retrievePrincipal(Principal principal) {
		return principal;
	}
}
