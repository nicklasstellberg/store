package com.example.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderItem {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderItemId;
	
	private int quantity;
	
	@ManyToOne(optional = true)
	@JsonIgnoreProperties ("orderitems") 
    @JoinColumn(name = "id") 
	private Product product;
	
	public OrderItem() {}

	public OrderItem(int quantity, Product product) {
		super();
		this.quantity = quantity;
		this.product = product;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem(Long orderItemId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
	}
}
