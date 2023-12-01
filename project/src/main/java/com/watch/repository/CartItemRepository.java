package com.watch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.watch.models.CartItem;

import jakarta.transaction.Transactional;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	CartItem findByCartIdAndProductId(Integer cartId,Integer productId);
	@Modifying
	@Query("DELETE FROM CartItem c WHERE c.cart.id =?1")
	void deleteByCartId(Integer carId);
}
