package com.watch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watch.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	
	
}
