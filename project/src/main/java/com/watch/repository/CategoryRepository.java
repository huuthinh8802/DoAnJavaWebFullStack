package com.watch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watch.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	@Query("SELECT c FROM Category c WHERE c.categoryName LIKE %?1% ")
	List<Category> searchCategory(String keyword);
	
	@Query("SELECT c FROM Category c WHERE c.categoryStatus =true")
	List<Category> getCategoryStatus();
	
}
