package com.example.store.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryid;
	
	@Size(min=5, max=30)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnoreProperties("category")
	private List<Product> products;
	
	public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
}
