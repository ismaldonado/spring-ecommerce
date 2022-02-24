package com.ecommerce.ecommerce.service.order;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Order> findAll() {
		return this.ordenRepository.findAll();
	}

	// en la bd está como string, hay que parsearla
	public String generateOrderNumber() {
		int number = 0;
		String orderNumber = "";
		List<Order> orders = findAll();
		List<Integer> numbers = new ArrayList<Integer>();
		orders.stream().forEach(o -> numbers.add(Integer.parseInt(o.getNumber())));

		if (numbers.isEmpty()) {
			number = 1;
		} else { // recorre la lista de numeros, comprara el máximo y lo devuelve.
			number = numbers.stream().max(Integer::compare).get();
			number++;
		}

		if (number < 10) {
			orderNumber = "00000" + String.valueOf(number);
		} else if (number < 100) {
			orderNumber = "0000" + String.valueOf(number);
		} else if (number < 1000) {
			orderNumber = "000" + String.valueOf(number);
		}
		return orderNumber;

	}
}
