package com.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.shopping.repository.IAccountRepository;
import com.shopping.security.userpincal.UserDetailService;
import com.shopping.service.impl.AccountDetailServiceImpl;
import com.sun.security.auth.UserPrincipal;

@Configuration
public class SecurityConfig{
	
//	@Autowired
//	AccountDetailServiceImpl accountDetailServiceImpl;

//	@Bean
//    public UserDetailsService userDetailsService() {
//		return new AccountDetailServiceImpl();
//    }
	
	@Bean
    UserDetailsService customUserDetailsService(IAccountRepository accounts) {
        return username -> {
			return (UserDetails) accounts.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        	};
    }
	
//	@Bean
//	public AuthenticationManager authenticationManager(
//	        AuthenticationConfiguration authConfig) throws Exception {
//	    return authConfig.getAuthenticationManager();
//	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.userDetailsService(null)
		http.csrf().ignoringAntMatchers("/api/public/**");
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/rest/**")
//			.permitAll()
//			.anyRequest()
//			.authenticated();
			
		http.authorizeRequests()
		.antMatchers("/products/**","/files/fffffff/**","/api/public/login","/api/public/abc").permitAll().anyRequest().authenticated();
		
		//		http.userDetailsService(username -> {
//			try {
//				Account account = iAccountRepository.findById(username).get();
//				if (account != null) {
//					String password = account.getPassword();
//					String[] roles = account.
//				}
//				return null;
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//			return null;
//		});
//		http
//				.authorizeHttpRequests((authorize) -> authorize
//						.antMatchers("").permitAll()
//						.anyRequest().authenticated()
//				)
//				.csrf().disable()
//				.cors().disable()
//				.httpBasic().disable()
//				.oauth2ResourceServer((oauth2) -> 
//						oauth2.jwt((jwt) -> jwt.jwtAuthenticationConverter(""))
//				)
		http.httpBasic();
		return http.build();
	}

//	@Bean
//    AuthenticationManager customAuthenticationManager(UserDetailsService userDetailsService, BCryptPasswordEncoder encoder) {
//        return authentication -> {
//            String username = authentication.getPrincipal() + "";
//            String password = authentication.getCredentials() + "";
//            
//            UserDetails user = userDetailsService.loadUserByUsername(username);
//            
//            if (!encoder.matches(password, user.getPassword())) {
//                throw new BadCredentialsException("Bad credentials");
//            }
//            
//            if (!user.isEnabled()) {
//                throw new DisabledException("User account is not active");
//            }
//            
//            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
//        };
//    }
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		
		return null;
	}

}
