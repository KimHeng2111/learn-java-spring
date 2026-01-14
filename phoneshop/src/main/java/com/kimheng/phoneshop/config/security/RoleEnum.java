package com.kimheng.phoneshop.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.kimheng.phoneshop.config.security.PermissionEnum.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(BRAND_WRITE,BRAND_READ,MODEL_READ,MODEL_WRITE)),
	SALE(Set.of(BRAND_READ,MODEL_READ));
	private Set<PermissionEnum> permissionEnums;
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> grantedAuthorities = this.permissionEnums.stream()
				.map(per -> new SimpleGrantedAuthority(per.name()))
				.collect(Collectors.toSet());
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return grantedAuthorities;
	}
}
