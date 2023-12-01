package com.watch.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watch.models.Cart;
import com.watch.models.Product;
import com.watch.models.User;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT COUNT(*) FROM Cart c WHERE c.user.id = ?1")
	Integer countId(Long idUser);
	
	List<Cart> findByUser(User user);
	
	
}
