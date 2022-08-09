package com.shopping.security.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collector;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import static java.util.stream.Collectors.joining;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	private static final String AUTHORITIES_KEY = "roles";
	private Object jwtProperties;
	
	
	@Value("${jwt.secret}")
	String JwtSecret;

	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		var secret = Base64.getEncoder().encodeToString(JwtSecret.getBytes());
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String createToken(Authentication authentication) {
		String username = authentication.getName();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Claims claims = Jwts.claims().setSubject(username);
		if (!authorities.isEmpty()) {
			claims.put(AUTHORITIES_KEY, authorities.stream().map(GrantedAuthority::getAuthority).collect(joining(",")));
		}

		Date now = new Date();
		Date validity = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);

		return Jwts.builder().setSubject(username).setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(this.secretKey, SignatureAlgorithm.HS256).compact();
	}

	public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token).getBody();
        
        Object authoritiesClaim = claims.get(AUTHORITIES_KEY);
        
        Collection<? extends GrantedAuthority> authorities = authoritiesClaim == null ? AuthorityUtils.NO_AUTHORITIES
                : AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim.toString());
        
        User principal = new User(claims.getSubject(), "", authorities);
        
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}
}
