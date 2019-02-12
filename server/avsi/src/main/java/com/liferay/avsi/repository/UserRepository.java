package com.liferay.avsi.repository;

import com.liferay.avsi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Leonardo Barros
 */
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndAndPassword(
		@Param("email") String email, @Param("password") String password);
}
