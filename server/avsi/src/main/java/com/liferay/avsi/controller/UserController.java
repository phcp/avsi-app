package com.liferay.avsi.controller;

import com.liferay.avsi.dto.UserDTO;
import com.liferay.avsi.entity.User;
import com.liferay.avsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo Barros
 */
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("users")
	public UserDTO register(@Valid @RequestBody UserDTO userDTO) {
		return map(userRepository.saveAndFlush(map(userDTO)));
	}

	@GetMapping("users")
	public Page<UserDTO> listPaginated(@PageableDefault(value = 10,page = 0) Pageable pageable) {
		Page<User> users = userRepository.findAll(pageable);

		List<UserDTO> userDTOS = new ArrayList<>();

		users.forEach(u -> userDTOS.add(map(u)));

		return new PageImpl<>(userDTOS);
	}

	private UserDTO map(User user) {
		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		userDTO.setRole(user.getRole());
		userDTO.setCreatedAt(user.getCreatedAt());
		userDTO.setUpdatedAt(user.getUpdatedAt());

		return userDTO;
	}

	private User map(UserDTO userDTO) {
		User user = null;

		if (userDTO.getId() > 0) {
			user = userRepository.findById(userDTO.getId()).get();
		}
		else {
			user = new User();
		}

		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setRole(userDTO.getRole());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

		return user;
	}
}
