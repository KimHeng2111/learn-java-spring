package com.kimheng.phoneshop.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SaleDTO {
	private List<SoldProduct> solProducts;
	private LocalDateTime dateSold;
}
