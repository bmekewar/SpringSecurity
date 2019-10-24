package com.bvm.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.bvm.security.model.LoggedInUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

@Component
public class JwtTokenGenerator {

	public String generate(LoggedInUser user) {

		Claims claims = Jwts.claims().setSubject(user.getUserName());
		claims.put("userId", String.valueOf(user.getId()));
		claims.put("role", String.valueOf(user.getRole()));

		String base64Encoded_secret_key = Encoders.BASE64
				.encode(SecurityConstants.TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, base64Encoded_secret_key).compact();
	}
}
