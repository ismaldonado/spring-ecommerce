package com.ecommerce.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product save(Product product) {

		return this.productRepo.save(product);
	}

	@Override // he tenido que quitar el optional
	public Product get(Integer id) {
		return this.productRepo.getById(id);
	}

	@Override
	public void update(Product product) {
		this.productRepo.save(product);

	}

	@Override
	public void delete(Integer id) {
		this.productRepo.deleteById(id);

	}

	@Override
	public List<Product> findAll() {
		return this.productRepo.findAll();
	}

}
