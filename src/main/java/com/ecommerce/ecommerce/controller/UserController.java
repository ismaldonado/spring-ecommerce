package com.ecommerce.ecommerce.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.user.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/register")
	public String create() {
		return "user/register";
	}

	@PostMapping("/save")
	public String save(User user) {
		user.setType("user");
		this.userService.save(user);
		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/access")
	public String access(User user, HttpSession session) {
		Optional<User> userAccess = this.userService.findByEmail(user.getEmail());
		if (userAccess.isPresent()) {
			session.setAttribute("userId", userAccess.get().getId());
			if (userAccess.get().getType().equals("ADMIN")) {
				return "redirect:/admin";
			} else {
				return "redirect:/";
			}
		}
		return "redirect:/";
	}

	@GetMapping("/purchase")
	public String getPurchase(Model model, HttpSession session) {
		model.addAttribute("session", session.getAttribute("userId"));
		return "user/purchase";

	}
}
