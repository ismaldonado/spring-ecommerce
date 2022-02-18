package com.ecommerce.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.UploadFileService;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	// @Autowired
	// private IUserService userService;

	@Autowired
	private UploadFileService upload;

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products/show";
	}

	@GetMapping("/create")
	public String create() {
		return "products/create";
	}

	@PostMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session)
			throws IOException {
		LOGGER.info("Este es el objeto Product {}", product);

		// User u =
		// userService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		// Product.setUser(u);

		// imagen
		if (product.getId() == null) { // cuando se crea un Product
			String nombreImg = upload.saveImg(file);
			product.setPicture(nombreImg);
		} else {

		}

		productService.save(product);
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = new Product();
		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();

		LOGGER.info("Product buscado: {}", product);
		model.addAttribute("product", product);

		return "products/edit";
	}

	@PostMapping("/update")
	public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
		Product p = new Product();
		p = productService.get(product.getId()).get();

		if (file.isEmpty()) { // editamos el Product pero no cambiamos la imagem

			product.setPicture(p.getPicture());
		} else {// cuando se edita tbn la imagen
				// eliminar cuando no sea la imagen por defecto
			if (!p.getPicture().equals("default.jpg")) {
				this.upload.deleteImg(p.getPicture());
			}
			String nombreImg = upload.saveImg(file);
			product.setPicture(nombreImg);
		}
		product.setUser(p.getUser());
		this.productService.update(product);
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Product p = new Product();
		p = this.productService.get(id).get();

		// eliminar cuando no sea la imagen por defecto
		if (!p.getPicture().equals("default.jpg")) {
			upload.deleteImg(p.getPicture());
		}

		this.productService.delete(id);
		return "redirect:/products";
	}

}