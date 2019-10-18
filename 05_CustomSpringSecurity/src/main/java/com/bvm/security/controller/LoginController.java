package com.bvm.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bvm.security.form.LoginForm;

@Controller
public class LoginController {

	@GetMapping(value = "/login", produces = { "text/html" })
	public String getLoginForm() {
		return "login";
	}

	@PostMapping(value = "/login", produces = { "text/html" })
	public String login(@ModelAttribute(name = "loginForm") LoginForm loginForm, Model model) {
		return "home";
	}

	@GetMapping("/logout-success")
	public String logoutPage() {
		return "logout";
	}

	@GetMapping("/")
	public String get() {
		return "home";
	}
}
