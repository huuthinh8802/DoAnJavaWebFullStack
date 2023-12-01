package com.watch.service;

import java.util.List;

import com.watch.models.Order;


public interface OrderService {

	List<Order> getAll();

	Boolean create(Order order);
	Order findById(Integer id);
}
