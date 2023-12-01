package com.watch.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.watch.models.Cart;
import com.watch.models.CartItem;
import com.watch.models.CustomUserDetails;
import com.watch.service.CartItemService;
import com.watch.service.CartService;
import com.watch.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@GetMapping
	public String showCart(Principal principal,Model model) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.findbyUser(customUserDetails.getUser());
		
		model.addAttribute("listCart", cart);
		return "cart";
	}
	@PostMapping
	public String addCart(@RequestParam("id") Integer idProduct, @RequestParam("quantity") Integer quantity,Principal principal) {
		if(principal == null) {
			return "redirect:/login";
		}
		Cart cart = new Cart();
		CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CartItem cartItem = new CartItem();
		//Kiem tra id cua user co trong db chua
		
		cart.setUser(customUserDetails.getUser());
		if(this.cartService.checkE(customUserDetails.getUser().getId()) == 0) {
			this.cartService.addCart(cart);
			cartItem.setCart(cart);
		}else {
			Cart cart1 = this.cartService.findbyUser(customUserDetails.getUser());
			
			cartItem.setCart(cart1);
		}
		
		//kiem tra xem san pham da ton tai hay chua?
		CartItem cartItemcheck = this.cartItemService.checkCartItem(cartItem.getCart().getId(), idProduct);
		if(cartItemcheck != null) {
			cartItemcheck.setQuantity(cartItemcheck.getQuantity()+quantity);
			this.cartItemService.add(cartItemcheck);
		}else {
			cartItem.setProduct(this.productService.findById(idProduct));
			cartItem.setQuantity(quantity);
			this.cartItemService.add(cartItem);
		}
		
		return "redirect:/cart";
	}
	@GetMapping("/cart-delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		if(this.cartItemService.delete(id)) {
			return "redirect:/cart";
		}else {
			return "redirect:/cart";
		}
	}
}
