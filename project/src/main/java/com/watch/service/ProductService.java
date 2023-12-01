package com.watch.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.watch.models.Category;
import com.watch.models.Product;

public interface ProductService {
	List<Product> getAll();

	Boolean create(Product product);
	
	Product findById(Integer id);
	Boolean update(Product product);
	Boolean delete(Integer id);
	List<Product> searchProdutc(String keyword);
	Page<Product> getAll(Integer pageNo);
	Page<Product> searchProduct(String keyword,Integer pageNo);
}
