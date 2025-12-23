package com.kimheng.phoneshop.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PriceDTO {
	@DecimalMin(value = "0.00001" , message = "Price of Product not less than 0")
	@NotNull
	private BigDecimal price;
}
