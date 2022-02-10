package com.ecommerce.ecommerce.model;

import java.util.Date;

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
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "number")
	private String number;
	@Column(name = "creationDate")
	private Date creationDate;
	@Column(name = "deliveryDate")
	private Date deliveryDate;
	@Column(name = "total")
	private double total;
	@ManyToOne
	private User user;
	@OneToOne(mappedBy = "order")
	private OrdenDetail detail;

	public Order(Integer id, String number, Date creationDate, Date deliveryDate, double total, User user,
			OrdenDetail detail) {
		this.id = id;
		this.number = number;
		this.creationDate = creationDate;
		this.deliveryDate = deliveryDate;
		this.total = total;
		this.user = user;
		this.detail = detail;
	}

}
