package com.liferay.avsi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * @author Leonardo Barros
 */
@Data
@EqualsAndHashCode
public class ProductDTO {
	private long id;

	@NotEmpty
	private String name;

	@Positive
	private double value;

	private Date createdAt;

	private Date updatedAt;
}
