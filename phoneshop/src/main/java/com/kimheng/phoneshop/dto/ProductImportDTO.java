package com.kimheng.phoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImportDTO {
	@NotNull(message= "prodcut Id Not Null")
	@Min(value = 1 , message = "Product ID  not less than 1")
	private Long productId;
	@Min(value = 1 , message = "Import Unit not less than 1")
	private Integer importUnit;
	@DecimalMin(value = "0.0001" , message = "price is not less than 0")
	private BigDecimal pricePerUnit;
	private LocalDateTime dateImport;
}
