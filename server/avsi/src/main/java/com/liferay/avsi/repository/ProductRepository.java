package com.liferay.avsi.repository;

import com.liferay.avsi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Leonardo Barros
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
