package com.example.store.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>  {
	
	List<Product> findByName(String Name);
	long deleteByName(String name);

}
