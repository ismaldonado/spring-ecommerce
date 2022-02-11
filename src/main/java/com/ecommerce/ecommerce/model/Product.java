package com.ecommerce.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "picture")
	private String picture;
	@Column(name = "ammount")
	private int ammount;
	@Column(name = "price")
	private double price;

	@ManyToOne
	private User user;

	public Product() {
	}

	public Product(Integer id, String name, String description, String picture, int ammount, double price, User user) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.ammount = ammount;
		this.price = price;
		this.user = user;
	}

}
