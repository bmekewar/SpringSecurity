package com.bvm.security;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bvm.security.model.LoggedInUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenValidator {

	public LoggedInUser validate(String token) {

		LoggedInUser loggedInUser = null;
		loggedInUser = new LoggedInUser();

		// retrieves payload from jwt token
		String base64Encoded_secret_key = Encoders.BASE64
				.encode(SecurityConstants.TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
		Claims body;
		try {
			body = Jwts.parser().setSigningKey(base64Encoded_secret_key).parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			log.error("JWT Token Signature do not match: {}", e.getMessage());
			throw new RuntimeException("JWT Token Signature do not match:" + e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token expired: {}", e.getMessage());
			throw new RuntimeException("JWT token expired: " + e.getMessage());
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
			log.error("Exception occured while parsing token: {}", e.getMessage());
			throw new RuntimeException("JWT token expired: " + e.getMessage());
		}

		if (body != null) {
			loggedInUser = new LoggedInUser();
			loggedInUser.setUserName(body.getSubject());
			loggedInUser.setId(Long.parseLong(body.get("userId").toString()));
			loggedInUser.setRole(body.get("role").toString());
		}

		return loggedInUser;
	}
}
