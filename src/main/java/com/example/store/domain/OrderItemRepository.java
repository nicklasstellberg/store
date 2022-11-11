package com.example.store.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	List<OrderItem> findByUser(User user);
	
}
