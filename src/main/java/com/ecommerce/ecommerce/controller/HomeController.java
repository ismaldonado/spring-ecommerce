package com.ecommerce.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private ProductService productService;

	// vable para imprimer cosas. no system
	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping()
	public String home(Model model) {
		model.addAttribute("products", this.productService.findAll());
		return "user/home";
	}

	@GetMapping("producthome/{id}") // se define la terminación de la ruta y el parámetro de búsqueda del producto
	public String productHome(@PathVariable Integer id, Model model) {
		Product product = new Product();
		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();
		model.addAttribute("product", product);
		log.info("id producto enviado como parametro {}", id);
		return "user/producthome"; // vista a la que redirecciona el endpoint
	}

	@PostMapping("/cart")
	public String addCart() {
		return "user/cart";
	}
}
