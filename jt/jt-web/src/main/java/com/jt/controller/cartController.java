package com.jt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class cartController {
	@Reference(timeout = 3000)
	private DubboCartService  cartService;
	@RequestMapping("/show")
	public String show(Model model) {
		Long userId=7L;
		List<Cart> cartList=cartService.findSelectcart(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	///cart/update/num/562379/9
	@RequestMapping("/update/num/{ItemId}/{num}")
	@ResponseBody
	public SysResult updataNum(Cart cart) {
		Long userId=7L;
		cart.setUserId(userId);
		cartService.updateCart(cart);
		return SysResult.success();
	}
	///cart/delete/562379.html
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Cart cart) {
		Long userId=UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cartService.deleteCart(cart);
		return "redirect:/cart/show.html";
	}
}
