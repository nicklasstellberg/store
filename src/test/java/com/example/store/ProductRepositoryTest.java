package com.example.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.store.domain.Product;
import com.example.store.domain.ProductRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository repository;
	
	@Test
    public void findByNameShouldReturnProduct() {
        List<Product> products = repository.findByName("Round Shovel");
        
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getDescription()).isEqualTo("A spade is a shovel that is used for moving soil and sand.");
    }
	
	@Test
    public void createNewProduct() {
    	Product product = new Product("inijet Vanilla Lighter", "The new generation of pocket lighters by S.T. Dupont.", 95.00, null);
    	repository.save(product);
    	assertThat(product.getId()).isNotNull();
    } 
	
	@Test
	@Transactional
	public void deleteProductByName() {
	    long deletedRecords = repository.deleteByName("Round Shovel");
	    assertThat(deletedRecords).isEqualTo(1);
	}

}
