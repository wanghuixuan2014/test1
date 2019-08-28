package com.jt.service;

import java.util.List;

import com.jt.pojo.Cart;

public interface DubboCartService {

	List<Cart> findSelectcart(Long userId);

	void updateCart(Cart cart);

	void deleteCart(Cart cart);

}
