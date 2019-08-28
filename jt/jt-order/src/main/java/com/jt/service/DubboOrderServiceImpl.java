package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service
public class DubboOrderServiceImpl implements DubboOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Override
	public String saveOrder(Order order) {
		String orderId=""+order.getUserId()+System.currentTimeMillis();
		Date date=new Date();
		order.setOrderId(orderId).setStatus(1);
		order.setCreated(date);
		order.setUpdated(date);
		orderMapper.insert(order);
		System.out.println("订单入库成功");
		OrderShipping orderShipping = order.getOrderShipping();
		orderShipping.setOrderId(orderId).setCreated(date).setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		System.out.println("物流订单成功");
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId).setCreated(date).setUpdated(date);
			orderItemMapper.insert(orderItem);
			System.out.println("商品订单");
			
		}
		return orderId;
	}
	@Override
	public Order findById(String id) {
		
		Order order=orderMapper.findById(id);
		return order;
	}
}
