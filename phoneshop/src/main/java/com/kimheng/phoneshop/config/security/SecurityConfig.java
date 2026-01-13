package com.kimheng.phoneshop.config.security;

import static com.kimheng.phoneshop.config.security.PermissionEnum.BRAND_WRITE;
import static com.kimheng.phoneshop.config.security.RoleEnum.ADMIN;
import static com.kimheng.phoneshop.config.security.RoleEnum.SALE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.kimheng.phoneshop.config.security.jwt.JwtLoginFilter;
@Configuration
@EnableMethodSecurity(prePostEnabled = true , jsr250Enabled = true , securedEnabled = true)
public class SecurityConfig{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)throws Exception{
		http.csrf(csrf -> csrf.disable())
		.addFilter(new JwtLoginFilter(authManager))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/", "/index.html", "/css/**", "/js/**").permitAll()
				//.requestMatchers("/brands").hasRole(BRA)
				.requestMatchers(HttpMethod.POST, "/brands/**").hasRole(BRAND_WRITE.name())
				//.requestMatchers(HttpMethod.GET, "/brands/**").hasRole("SALE")
				.anyRequest()
				.authenticated());
		return http.build();
	}
	
	@Bean
	public UserDetailsService detailsService() {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin0308"))
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
		UserDetails sale = User.builder()
				.username("pheary")
				.password(passwordEncoder.encode("pheary0308"))
				.authorities(SALE.getGrantedAuthorities())
				.build();
		return new InMemoryUserDetailsManager(admin,sale);
	}
}
