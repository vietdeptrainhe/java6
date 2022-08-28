package com.shopping.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.shopping.jwt.JwtAuthenticationFilter;
import com.shopping.security.userprincipal.UserDetailService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig{

//	private final AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
    UserDetailService userDetailsService;
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
//		.cors().disable()
		.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/login","/products/**","/products/views","/files/**").permitAll()
//		.antMatchers("/rest/authorities").hasAnyRole("Directors")
//		.anyRequest().authenticated()
		.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("Staffs","Directors")
		.antMatchers("/authorities").hasAnyRole("Directors")
		.anyRequest().permitAll()
		.and()
		.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(authException.getMessage());
        }).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Autowired
    void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
}
