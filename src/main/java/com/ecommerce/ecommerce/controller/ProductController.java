package com.ecommerce.ecommerce.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.UploadFileService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private UploadFileService upload;

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
	public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
		User u = new User(1, "", "", "", "", "", "", "", null, null);
		product.setUser(u);

		// subir imagen
		if (product.getId() == null) { // si se crea producto por 1ª vez
			String imgName = upload.saveImg(file);
			product.setPicture(imgName);
		} else {
			if (file.isEmpty()) { // cuando editamos el producto pero no cambiamos la imagen
				Product p = new Product(); // creamos el producto nuevo que se vaa guardar
				p = this.productService.get(product.getId()).get(); // se obtiene la imagen del producto anterior
				product.setPicture(p.getPicture()); // se le asigna al nuevo objeto
			}
		}
		this.productService.save(product);
		return "redirect:/products";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Product product = new Product();
		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();

		LOGGER.info("Producto buscado: {}", product);
		model.addAttribute("product", product);

		return "products/edit";
	}

	@PostMapping("/update")
	public String update(Product producto) {
		this.productService.update(producto);
		return "redirect:/products";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.productService.delete(id);
		return "redirect:/products";
	}

}
