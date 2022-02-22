package com.ecommerce.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orderDetails")
public class OrdenDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "price")
	private double price;
	@Column(name = "ammount")
	private double ammount;
	@Column(name = "total")
	private double total;
	@OneToOne
	private Order order;

	@ManyToOne
	private Product product;

	public OrdenDetail() {

	}

	public OrdenDetail(Integer id, String name, double price, double ammount, Order order, Product product) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.ammount = ammount;
		this.order = order;
		this.product = product;
	}

}
