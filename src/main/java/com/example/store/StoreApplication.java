package com.example.store;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.store.domain.Category;
import com.example.store.domain.CategoryRepository;
import com.example.store.domain.OrderItem;
import com.example.store.domain.OrderItemRepository;
import com.example.store.domain.Product;
import com.example.store.domain.ProductRepository;
import com.example.store.domain.User;
import com.example.store.domain.UserRepository;

@SpringBootApplication
public class StoreApplication {
	private static final Logger log = LoggerFactory.getLogger(StoreApplication.class);
	

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner productDemo(ProductRepository repository, CategoryRepository crepository, UserRepository urepository, OrderItemRepository oirepository) {
		return (args) -> {
			SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
			Category category1 = new Category("Shovels");
			crepository.save(category1);
			Category category2 = new Category("Masks");
			crepository.save(category2);
			log.info("save a couple of products");
			Product product1 = (new Product("Round Shovel", "A spade is a shovel that is used for moving soil and sand.", 18.95, category1));
			repository.save(product1);
			Product product2 =(new Product("Pyromaniac Mask", "This over-the-head mask provides a seamless fit when worn with a variety of clothing or robes.", 105.14, category2));
			repository.save(product2);
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", fdate.parse("01.10.2022"));
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", fdate.parse("01.10.2022"));
			urepository.save(user1);
			urepository.save(user2);
			OrderItem orderItem1 = new OrderItem(2, fdate.parse("01.10.2022"), product1, user1);
			oirepository.save(orderItem1);
			log.info("fetch all products");
			for (Product product : repository.findAll()) {
				log.info(product.toString());
			}

		};
	}

}
