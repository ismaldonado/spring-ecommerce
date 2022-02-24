package com.ecommerce.ecommerce.service.orderDetail;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.OrdenDetail;
import com.ecommerce.ecommerce.repository.IOrderDetailRepository;

@Service
public class OrdenDetailServiceImpl implements IOrderDetailService {
	private IOrderDetailRepository orderDetailReporsitory;

	@Override
	public OrdenDetail save(OrdenDetail detail) {
		return this.orderDetailReporsitory.save(detail);
	}

}
