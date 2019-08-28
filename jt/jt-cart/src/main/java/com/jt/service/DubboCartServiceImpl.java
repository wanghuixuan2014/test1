package com.jt.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;

@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService {

	@Autowired
	private CartMapper  cartMapper;

	@Override
	public List<Cart> findSelectcart(Long userId) {
		QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("user_Id", userId);
		
		return cartMapper.selectList(queryWrapper);
	}

	@Override
	public void updateCart(Cart cart) {
		Cart cart2 = new Cart();
		cart2.setNum(cart.getNum()).setUpdated(new Date());
		UpdateWrapper<Cart> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("user_id", cart.getUserId()).eq("item_id", cart.getItemId());
		cartMapper.update(cart2, updateWrapper);
	}

	@Override
	public void deleteCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper=new QueryWrapper<>(cart);
		
		
		
		cartMapper.delete(queryWrapper);
	}

	
}
