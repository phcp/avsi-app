package com.liferay.avsi.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leonardo Barros
 */
@Configuration
public class RepositoryConfiguration {

	@Bean
	UserRepositoryEventHandler userEventHandler() {
		return new UserRepositoryEventHandler();
	}
}
