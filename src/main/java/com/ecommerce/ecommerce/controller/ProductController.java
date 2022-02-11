package com.ecommerce.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	// private final Logger LOGGER =
	// LoggerFactory.getLogger(ProductController.class);

	@GetMapping("")
	public String show(Model model) { // model es un objeto que lleva info desde el backend hasta la vista
		model.addAttribute("products", this.productService.findAll());
		// el primer atributo es el nombre en la vista y el segundo el objeto que trae
		return "products/show";
	}

	@GetMapping("/create") // fragmento de la url que se muestra
	public String create() {
		return "products/create"; // vista que carga
	}

	@PostMapping("/save")
	public String save(Product product) {
		User u = new User(1, "", "", "", "", "", "", "", null, null);
		product.setUser(u);
		this.productService.save(product);
		return "redirect:/products";
	}

}
