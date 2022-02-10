package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Product;

public interface ProductService {

	public Product save(Product product);

	public Product get(Integer id);

	public void update(Product product);

	public void delete(Integer id);

}
