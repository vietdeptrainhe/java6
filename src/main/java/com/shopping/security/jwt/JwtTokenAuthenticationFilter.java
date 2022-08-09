package com.shopping.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

//import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.web.filter.GenericFilterBean;

import com.shopping.service.impl.AccountDetailServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends GenericFilterBean {

	public static final String HEADER_PREFIX = "Bearer ";
	
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AccountDetailServiceImpl accountDetailServiceImpl;
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String token = resolveToken((HttpServletRequest) req);
		if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            UserDetails userDetails = accountDetailServiceImpl.loadUserByUsername(jwtTokenProvider.getAuthentication(token).getName());
            
            if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
		chain.doFilter(req, res);
	}
	
	private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
