package com.watch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.watch.models.Product;

import jakarta.transaction.Transactional;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT c FROM Product c WHERE c.productName LIKE %?1% ")
	List<Product> searchProduct(String keyword);
	
}
