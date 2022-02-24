package com.ecommerce.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepo;

	public Product save(Product product) {

		return this.productRepo.save(product);
	}

	public Optional<Product> get(Integer id) {
		return this.productRepo.findById(id);
	}

	public void update(Product product) {
		this.productRepo.save(product);

	}

	public void delete(Integer id) {
		this.productRepo.deleteById(id);

	}

	public List<Product> findAll() {
		return this.productRepo.findAll();
	}

}
