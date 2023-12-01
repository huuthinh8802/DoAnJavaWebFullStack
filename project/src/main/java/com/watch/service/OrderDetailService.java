package com.watch.service;


import java.util.List;

import com.watch.models.Order;
import com.watch.models.OrderDetail;

public interface OrderDetailService {

	Boolean add(OrderDetail detail);
	List<OrderDetail> getByOrder(Order order);
}
