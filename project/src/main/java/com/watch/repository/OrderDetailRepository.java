package com.watch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watch.models.Order;
import com.watch.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	List<OrderDetail> findByOrder(Order order);
}
