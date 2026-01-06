package com.kimheng.phoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.kimheng.phoneshop.entity.ProductImportHistory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDTO {
	private String productName;
	private Integer totalUnit;
	private BigDecimal totalAmount;
}
