package com.example.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderItem {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderItemId;
	
	@NotNull
    @Min(1)
	@Max(10)
	private int quantity;
	
	@ManyToOne(optional = true)
	@JsonIgnoreProperties ("orderitems") 
    @JoinColumn(name = "id") 
	private Product product;
	
	@ManyToOne(optional = true)
	@JsonIgnoreProperties ("orderitems") 
    @JoinColumn(name = "userid") 
	private User user;
	
	public OrderItem() {}
	
	public OrderItem(int quantity, Product product, User user) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.user = user;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	
}
