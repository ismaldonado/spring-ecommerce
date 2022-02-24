package com.ecommerce.ecommerce.service.order;

import java.util.List;

import com.ecommerce.ecommerce.model.Order;

public interface IOrderService {
	List<Order> findAll();

	Order save(Order order);
}
