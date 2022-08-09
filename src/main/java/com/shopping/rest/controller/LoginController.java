package com.shopping.rest.controller;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.payload.request.LoginRequest;
import com.shopping.repository.IAccountRepository;
import com.shopping.security.jwt.JwtTokenProvider;
import com.shopping.service.jwt.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequestMapping("api/public")
@RequiredArgsConstructor
public class LoginController {
	
//	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private final IAccountRepository accounts;
	
	@Value("${jwt.secret}")
	String secret;
	
	
	@PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest data) {
        try {
            String username = data.getUsername();
//            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, 
//            		data.getPassword()
//            		));
//            String token = jwtTokenProvider.createToken(authentication);
//            Map<Object, Object> model = new HashMap<>();
//            model.put("username", username);
//            model.put("token", token);
//            return ResponseEntity.ok(model);
            return null;
        } catch (AuthenticationException e) {
        	e.printStackTrace();
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
	
	@SuppressWarnings("deprecation")
	@PostMapping(value = "/abc",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login2(
			@RequestBody LoginRequest data
//			@RequestParam("username") String username, @RequestParam("password") String password 
			) {
		
		try {
//			SecureRandom random = new SecureRandom();
//		    byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
//		                                 // the 256 required bits 
//		    random.nextBytes(bytes);
//		    var encoder = Base64.getUrlEncoder().withoutPadding();
			
			Claims claim= Jwts.claims() 
	                .setSubject(data.getUsername());
	            claim.put("userId", String.valueOf(99));
			return ResponseEntity.ok(Jwts.builder()
					.setSubject(data.getUsername())
					.addClaims(claim)
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
					.signWith(SignatureAlgorithm.HS256, secret.getBytes())
					.compact());			
//					.setIssuedAt(new Date(System.currentTimeMillis()))
//					.setIssuer("vietnguyenphuoc")
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
