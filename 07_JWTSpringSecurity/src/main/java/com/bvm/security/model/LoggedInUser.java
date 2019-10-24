package com.bvm.security.model;

import lombok.Data;

@Data
public class LoggedInUser {

	public Long id;
	public String userName;
	public String password;
	public String role;
}
