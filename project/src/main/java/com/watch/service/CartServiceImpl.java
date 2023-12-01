package com.watch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watch.models.Cart;
import com.watch.models.CartItem;
import com.watch.models.User;
import com.watch.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepository;
	@Override
	public Boolean addCart(Cart cart) {
		try {
			this.cartRepository.save(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	@Override
	public Integer checkE(Long idUser) {
		// TODO Auto-generated method stub
		return this.cartRepository.countId(idUser);
	}
	@Override
	public Cart findbyUser(User user) {
		// TODO Auto-generated method stub
		return cartRepository.findByUser(user).get(0);
	}
	@Override
	public List<CartItem> finByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
