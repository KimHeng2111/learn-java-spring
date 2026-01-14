package com.kimheng.phoneshop.config.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenVerifyFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		
//		if(authorization == null || !authorization.startsWith("Bearer")) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		
//		Jws<Claims> claimsJws = Jwts.parser()
//		.setSigningKey(config.getSigningKey())
//		.parseClaimsJws(token);
//		Claims body = claimsJws.getBody();
//		String username = body.getSubject();
//		List<Map<String,String>> authorities =  (List<Map<String,String>>)body.get("authorities");
//		Set<SimpleGrantedAuthority> collect = authorities.stream().map(athu -> new SimpleGrantedAuthority(athu.get("authority"))).collect(Collectors.toSet());
//		Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,collect);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
		JwtConfig config = new JwtConfig();
		String authorization = request.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authorization.replaceAll("Bearer ", "");
		try {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(config.getSigningKey()).parseClaimsJws(token);
			Claims body = claimsJws.getBody();
			String username = body.getSubject();
			Object rawAuthorities = body.get("authorities");
			if (rawAuthorities instanceof List<?> list) {
			    // Safely process or convert each element
			    List<Map<String, String>> authorities = list.stream()
			        .filter(Map.class::isInstance)
			        .map(obj -> (Map<String, String>) obj) // Still unchecked, but safer after filter
			        .toList();
			    Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
						.map(auth -> new SimpleGrantedAuthority(auth.get("authority"))).collect(Collectors.toSet());
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				System.out.println("User: " + auth.getName());
				System.out.println("Authorities: " + auth.getAuthorities());

			}
			
		} catch (JwtException e) {
			SecurityContextHolder.clearContext();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
			return;
		}

		filterChain.doFilter(request, response);

	}

}
