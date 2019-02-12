package com.liferay.avsi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * @author Leonardo Barros
 */
@Entity(name = "products")
@Data
@EqualsAndHashCode
public class Product extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	private String name;

	@Positive
	private double value;
}
