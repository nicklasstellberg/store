package com.example.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Product {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(min=5, max=30)
	private String name;
	
	@Size(min=5, max=255)
	private String description;
	
	private double price;
	
	@ManyToOne(optional = true)
	@JsonIgnoreProperties ("products") 
    @JoinColumn(name = "categoryid") 
    private Category category;
	
	public Product() {}
	
	public Product(String name, String description, double price, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	
}
