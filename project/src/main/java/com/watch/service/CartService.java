package com.watch.service;

import java.util.List;

import com.watch.models.Cart;
import com.watch.models.CartItem;
import com.watch.models.Product;
import com.watch.models.User;

public interface CartService {
	Boolean addCart(Cart cart);
	Integer checkE(Long idUser);
	Cart findbyUser(User user);
	List<CartItem> finByCartId(Integer cartId);
}
