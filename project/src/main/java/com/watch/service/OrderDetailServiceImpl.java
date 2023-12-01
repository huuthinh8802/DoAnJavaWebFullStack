package com.watch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watch.models.Order;
import com.watch.models.OrderDetail;
import com.watch.repository.OrderDetailRepository;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Override
	public Boolean add(OrderDetail detail) {
		try {
			this.orderDetailRepository.save(detail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<OrderDetail> getByOrder(Order order) {
		// TODO Auto-generated method stub
		return this.orderDetailRepository.findByOrder(order);
	}

}
