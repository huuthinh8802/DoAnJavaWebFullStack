package com.watch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.watch.models.ImageProduct;

import jakarta.transaction.Transactional;

@Transactional
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer>{
	@Modifying
	@Query("DELETE FROM ImageProduct i WHERE i.product.id =?1")
	void deleteByProductId(Integer id);
}
