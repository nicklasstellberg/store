package com.example.store;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.store.domain.User;
import com.example.store.domain.UserRepository;

@ExtendWith(SpringExtension.class) 
@DataJpaTest
public class UserRepositoryTest {
	@Autowired
    private UserRepository repository;
	
	@Test
    public void findByNameShouldReturnCategory() {
        User user = repository.findByUsername("user");
        
        assertThat(user).isNotNull();
    }
	
	@Test
    public void createNewUser() throws ParseException {
		SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");
    	User user = new User("Nicklas", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", fdate.parse("01.10.2022"));
    	repository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
	@Test
	@Transactional
	public void deleteUserByUsername() {
	    long deletedRecords = repository.deleteByUsername("user");
	    assertThat(deletedRecords).isEqualTo(1);
	}

}