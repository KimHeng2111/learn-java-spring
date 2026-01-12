package com.kimheng.phoneshop.config.security;

//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read");
	private String description;
	PermissionEnum(String des) {
		this.description = des;
	}
	public String getDescription() {
		return this.description;
	}
}