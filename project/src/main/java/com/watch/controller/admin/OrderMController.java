package com.watch.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watch.models.Order;
import com.watch.models.OrderDetail;
import com.watch.service.OrderDetailService;
import com.watch.service.OrderService;

@Controller
@RequestMapping("/admin")
public class OrderMController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@RequestMapping("order")
	public String orders(Model model) {
		
		List<Order> orders = this.orderService.getAll();
		model.addAttribute("orders", orders);
		return "admin/order/index";
	}
	@RequestMapping("detail/{id}")
	public String detail(Model model,@PathVariable("id") Integer idCart) {
		
		Order order = this.orderService.findById(idCart);
		List<OrderDetail> listDetail = this.orderDetailService.getByOrder(order);
		
		model.addAttribute("order", order);
		model.addAttribute("listDetail", listDetail);
		return "admin/order/detail";
	}
	@RequestMapping("updateOrder")
		public String update(@RequestParam("id") Integer idOrder,@RequestParam("status") Integer status) {
		
		System.out.println(idOrder);
		Order order = this.orderService.findById(idOrder);
		System.out.println(order.getUser().getEmail());
		order.setStatus(status);
		this.orderService.create(order);
		
		
		return "redirect:/admin/order";
	}
}
