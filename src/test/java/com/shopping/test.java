package com.shopping;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.shopping.entity.Account;
import com.shopping.repository.IAccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class test {
	
	@Autowired
	IAccountRepository accountRepository;
	
	@Test
	public void  testCreate() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawpassword = "123";
		String encodePassword = passwordEncoder.encode(rawpassword);
		Account account = new Account();
		account.setUsername("viet1");
		account.setPassword(encodePassword);
		Account account1 = accountRepository.save(account);
		assertThat(account1).isNotNull();
	}
}
