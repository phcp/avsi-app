package com.liferay.avsi.security;

import com.liferay.avsi.entity.User;
import com.liferay.avsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Leonardo Barros
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String email = authentication.getName();
		String password = authentication.getCredentials().toString();

		password = passwordEncoder.encode(password);

		User user = userRepository.findByEmailAndAndPassword(email, password);

		if (user != null) {
			return new UsernamePasswordAuthenticationToken(
				email, password, Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
		}
		else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
