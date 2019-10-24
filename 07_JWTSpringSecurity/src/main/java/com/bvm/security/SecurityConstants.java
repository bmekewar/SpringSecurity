package com.bvm.security;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 864000000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String TOKEN_SECRET = "SH512+works+fine+with+min+512+bit+encoded+string+"
			+ "this+is+my+secret+key+"
			+ "try+to+make+it+512+in+length";
}