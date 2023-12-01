package com.watch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watch.models.CartItem;
import com.watch.service.CartItemService;

@RestController
@RequestMapping("/cartItemApi")
public class CartItemApiController {

	@Autowired
	private CartItemService cartItemService;
	@PutMapping("/{id}")
	CartItem update(@PathVariable("id") Integer id, @RequestBody CartItem cartItem) {
		
		CartItem cartItemOld = this.cartItemService.findId(id);
		cartItemOld.setQuantity(cartItem.getQuantity());
		return this.cartItemService.update(cartItemOld);
	}
	
}
