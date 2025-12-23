package com.kimheng.phoneshop.dto;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getQty();
	BigDecimal getTotalAmount();
	
}
