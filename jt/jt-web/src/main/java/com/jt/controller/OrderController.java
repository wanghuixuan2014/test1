package com.jt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/order")
public class OrderController {

	
	@Reference(timeout = 3000,check = false)
	private DubboOrderService OrderService;
	
	@Reference(timeout = 3000,check = false)
	private DubboCartService cartService;
	//http://www.jt.com/order/create.html
	@RequestMapping("/create")
	public String create(Model model) {
		Long userId=UserThreadLocal.get().getId(); 
		List<Cart> carts = cartService.findSelectcart(userId);
		model.addAttribute("carts",carts);
		return "order-cart";
	}
	
	
	
	@RequestMapping("/success")
	
	public SysResult success(String id,Model model) {
	Order retult=	OrderService.findById(id);
		model.addAttribute("order", retult);
		
		return SysResult.success(retult);
	}
	
	
	
	
	
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		
		Long userId=UserThreadLocal.get().getId();
		order.setUserId(userId);
		String retult=OrderService.saveOrder(order);
		//model.addAttribute(attributeName, attributeValue);
		return SysResult.success(retult);
	}
	
}
