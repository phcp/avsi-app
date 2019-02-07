package com.liferay.avsi.repository;

import com.liferay.avsi.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Leonardo Barros
 */
@RepositoryEventHandler(User.class)
public class UserRepositoryEventHandler {

	@HandleBeforeSave
	public void handleUserSave(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
