package com.liferay.avsi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Leonardo Barros
 */
@Data
public class UserDTO {
	private long id;

	@NotEmpty
	private String email;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;

	@NotEmpty
	private String role;

	private Date createdAt;

	private Date updatedAt;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
