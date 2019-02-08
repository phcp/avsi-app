package com.liferay.avsi.repository;

import com.liferay.avsi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Leonardo Barros
 */
@Component
@RepositoryEventHandler(User.class)
public class UserRepositoryEventHandler {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@HandleBeforeSave
	public void handleUserSave(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	@HandleBeforeCreate
	public void handleUserCreate(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}
}
