package com.kimheng.phoneshop.config.security.jwt;

import java.io.IOException;
import java.security.Key;
import java.time.LocalDate;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	//Authentication User for 
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LoginRequest user = mapper.readValue(request.getInputStream(), LoginRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		JwtConfig config = new JwtConfig();
		Key signingKey = config.getSigningKey();
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.setIssuedAt(new java.util.Date())
				.claim("authorities", authResult.getAuthorities())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(7)))
				.setIssuer("PhoneShop.com")
				.signWith(signingKey)
				.compact();
		response.setHeader("Authorization", "Bearer " + token);
		response.setContentType("application/json");
		response.getWriter().write("{\"token\": \"" + token + "\"}");

	}

}
