package com.bvm.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/logout-success")
	public String logoutPage() {
		return "logout";
	}

	@GetMapping("/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
	
}
