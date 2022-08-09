package com.shopping.service.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtService {
	
	public static String secretKey = "NguyenPhuocViet";
	
	public static String createToken(
//			Map<String, Object> claims, 
			String subject) {
		return Jwts.builder()
//				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512,secretKey)
				.compact();
	}
	
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return true;
//	}
//
//	private String extractUsername(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//
//	private String extractClaim(String token, Object object) {
//		
//		return null;
//	}
}
