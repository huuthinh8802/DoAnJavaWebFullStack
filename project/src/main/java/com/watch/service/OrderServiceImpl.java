package com.watch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watch.models.Order;
import com.watch.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return this.orderRepository.findAll();
	}

	@Override
	public Boolean create(Order order) {
		try {
			this.orderRepository.save(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Order findById(Integer id) {
		// TODO Auto-generated method stub
		return this.orderRepository.findById(id).get();
	}

}
