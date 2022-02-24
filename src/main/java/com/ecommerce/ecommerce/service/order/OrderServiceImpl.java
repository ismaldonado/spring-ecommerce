package com.ecommerce.ecommerce.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.repository.IOrdenRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private IOrdenRepository ordenRepository;

	@Override
	public Order save(Order order) {
		return ordenRepository.save(order);
	}

}
