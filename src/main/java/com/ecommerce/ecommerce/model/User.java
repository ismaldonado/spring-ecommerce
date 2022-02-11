package com.ecommerce.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "phone")
	private String phone;
	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "user") // relación de esta clase con el atributo
	private List<Product> products;

	@OneToMany(mappedBy = "user")
	private List<Order> order;

	public User() {

	}

	public User(Integer id, String name, String username, String email, String password, String address, String phone,
			String type, List<Product> products, List<Order> order) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.type = type;
		this.products = products;
		this.order = order;
	}

}
