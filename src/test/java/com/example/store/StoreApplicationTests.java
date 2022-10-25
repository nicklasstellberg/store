package com.example.store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.store.web.CategoryController;
import com.example.store.web.ProductController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoreApplicationTests {
	
	@Autowired
	private ProductController productController;
	
	@Autowired
	private CategoryController categoryController;
	
	@Test
	public void contextLoads() {
		assertThat(productController).isNotNull();
	}

	@Test
	public void contextLoads2() {
		assertThat(categoryController).isNotNull();
	}

}
