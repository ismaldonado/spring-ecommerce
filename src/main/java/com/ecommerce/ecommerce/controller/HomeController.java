package com.ecommerce.ecommerce.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.ecommerce.model.OrdenDetail;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.order.IOrderService;
import com.ecommerce.ecommerce.service.orderDetail.IOrderDetailService;
import com.ecommerce.ecommerce.service.product.IProductService;
import com.ecommerce.ecommerce.service.user.IUserService;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	private IProductService productService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;

	// vable para imprimer cosas. no system
	// private final Logger log = LoggerFactory.getLogger(HomeController.class);

	// para almacenar los distintos detalles de los elementos del pedido
	private List<OrdenDetail> ordenDetail = new ArrayList<OrdenDetail>();

	// almacena el pedido entero
	Order order = new Order();

	@GetMapping()
	public String home(Model model) {
		model.addAttribute("products", this.productService.findAll());
		return "user/home";
	}

	// ver el detalle del producto
	@GetMapping("producthome/{id}") // se define la terminación de la ruta y el parámetro de búsqueda del producto
	public String productHome(@PathVariable Integer id, Model model) {
		Product product = new Product();
		Optional<Product> optionalProduct = productService.get(id);
		product = optionalProduct.get();
		model.addAttribute("product", product);
		// log.info("id producto enviado como parametro {}", id);
		return "user/producthome"; // vista a la que redirecciona el endpoint
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer ammount, Model model) {

		// recupera producto con id (tipo producto añadido)
		Optional<Product> optionalProduct = this.productService.get(id);
		// se asigna a vble tipo product
		Product product = new Product();
		product = optionalProduct.get();
		// se crea detalle
		OrdenDetail detail = new OrdenDetail(); // ticket
		// se da valor a las vbles
		detail.setAmmount(ammount); // cantidad de cada producto
		detail.setPrice(product.getPrice());
		detail.setName(product.getName());
		detail.setTotal(product.getPrice() * ammount); // cantidad total productos iguales
		detail.setProduct(product);

		// comprobar que no se añada dos veces
		Integer productId = product.getId();
		boolean exist = this.ordenDetail.stream().anyMatch(p -> p.getProduct().getId() == productId);
		if (!exist) {
			ordenDetail.add(detail);
		}

		// añade detalle al pedido (orden)
		// this.ordenDetail.add(detail);

		// cada elemento del ticket se devuelve como doubleStream después de obtener el
		// total y sumarlo
		double subtotal = ordenDetail.stream().mapToDouble(dt -> dt.getTotal()).sum();
		order.setTotal(subtotal);

		// pintar en la vista
		model.addAttribute("cart", ordenDetail);
		model.addAttribute("order", order);
		return "user/cart";
	}

	// borrar producto carrito
	@GetMapping("/delete/cart/{id}")
	public String deteleProductCart(@PathVariable Integer id, Model model) {
		// lista nueva productos
		List<OrdenDetail> orderToUpdate = new ArrayList<OrdenDetail>();
		// borrar producto lista original
		// recorre la lista original y cada elemento lo guarda en detail
		for (OrdenDetail detail : ordenDetail) {
			if (detail.getProduct().getId() != id) { // si el producto es ! del que se quiere borrar
				orderToUpdate.add(detail); // se añade a la lista nueva
			}
		}
//		this.ordenDetail.stream()
//			.filter(detail -> detail.getProduct().getId() != id)
//			.map(detail -> orderToUpdate.add(detail));

		// lista actualizada
		ordenDetail = orderToUpdate;

		double subtotal = ordenDetail.stream().mapToDouble(dt -> dt.getTotal()).sum();

		order.setTotal(subtotal);

		// pintar en la vista
		model.addAttribute("dt", ordenDetail);
		model.addAttribute("order", order);
		return "user/cart";
	}

	@GetMapping("/getCart")
	public String getCart(Model model) {
		model.addAttribute("cart", ordenDetail);
		model.addAttribute("order", order);
		return "user/cart";
	}

	@GetMapping("/resumen-orden")
	public String getResumen(Model model, Integer id) {

		User user = this.userService.findById(1).get();
		// pintar productos en la vista detalle orden
		model.addAttribute("dt", ordenDetail);
		model.addAttribute("order", order);
		model.addAttribute("user", user);
		return "user/resumenorden";
	}

	@GetMapping("/saveOrder")
	public String saveOrder() {
		// user dueño del pedido
		User user = this.userService.findById(1).get();
		Date creationDate = new Date();
		order.setCreationDate(creationDate);
		order.setNumber(orderService.generateOrderNumber());
		order.setUser(user);
		this.orderService.save(order);

		// guardar detalle
		this.ordenDetail.stream().map(dt -> this.orderDetailService.save(dt));
		// limpiar lista y orden
		order = new Order();
		ordenDetail.clear();
		return "redirect:/";
	}

	@PostMapping("/search")
	public String search(@RequestParam String productName, Model model) {
		List<Product> products = this.productService.findAll().stream().filter(p -> p.getName().contains(productName))
				.collect(Collectors.toList());
		model.addAttribute("products", products);
		return "user/home";
	}

}
