package com.kimheng.phoneshop.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSoldDTO {
	private Long productId;
	private String productName;
	private Long qty;
	private BigDecimal totalAmount;
}
