package com.watch.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.watch.models.Cart;
import com.watch.models.CartItem;
import com.watch.models.CustomUserDetails;
import com.watch.models.Order;
import com.watch.models.OrderDetail;
import com.watch.service.CartItemService;
import com.watch.service.CartService;
import com.watch.service.OrderDetailService;
import com.watch.service.OrderService;
import com.watch.service.ProductService;

@Controller 
public class OrderController {

	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired 
	private OrderDetailService orderDetailService;
	
	@RequestMapping("checkout")
	public String checkout(Principal principal, Model model) {

		if (principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cart cart = this.cartService.findbyUser(customUserDetails.getUser());
		
		model.addAttribute("user", customUserDetails.getUser());
		model.addAttribute("listCart", cart);
		
		Order order = new Order();
		order.setUser(customUserDetails.getUser());
		model.addAttribute("order", order);
		return "checkout";
	}
	@RequestMapping("orderOverview")
	public String orderOverview(Principal principal, Model model) {

		if (principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cart cart = this.cartService.findbyUser(customUserDetails.getUser());
		
		model.addAttribute("user", customUserDetails.getUser());
		model.addAttribute("listCart", cart);
		model.addAttribute("totalPrice", cart.totalPrice());
		Order order = new Order();
		order.setUser(customUserDetails.getUser());
		model.addAttribute("order", order);
		
		return "orderOverview";
	}
	@RequestMapping("payment")
	public String payment(Principal principal, Model model) {

		
		return "payment";
	}
	@RequestMapping("postChekout")
	public String orderComplete(Principal principal,@ModelAttribute("order") Order order,Model model) {

		if (principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Cart cart = this.cartService.findbyUser(customUserDetails.getUser());
		order.setUser(customUserDetails.getUser());
		order.setCreateAt(new Date());
		order.setStatus(0);
		if(this.orderService.create(order)) {
			for (CartItem cartItem : cart.getCartItems()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(order);
				orderDetail.setPrice(cartItem.getProduct().getPrice());
				orderDetail.setProduct(cartItem.getProduct());
				orderDetail.setQuantity(cartItem.getQuantity());
				this.orderDetailService.add(orderDetail);
				
			}
		}
		
		this.cartItemService.deleteByCartId(cart.getId());
		
		return "orderComplete";
	}

}
