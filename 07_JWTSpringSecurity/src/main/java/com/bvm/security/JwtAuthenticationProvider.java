package com.bvm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bvm.security.model.JwtUserDetails;
import com.bvm.security.model.LoggedInUser;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtTokenValidator validator;

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {

		// convert this token to JWT for validation
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) token;
		String jwtToken = jwtAuthenticationToken.getToken();

		LoggedInUser loggedInUser = validator.validate(jwtToken);
		if (loggedInUser == null) {
			throw new RuntimeException("JWT Token is not valid!!");
		}

		return new JwtUserDetails(jwtToken, loggedInUser);
	}

}
