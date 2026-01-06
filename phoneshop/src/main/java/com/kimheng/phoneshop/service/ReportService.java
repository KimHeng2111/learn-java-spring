package com.kimheng.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import com.kimheng.phoneshop.dto.ExpenseDTO;
import com.kimheng.phoneshop.dto.ProductSold;
import com.kimheng.phoneshop.dto.ProductSoldDTO;

public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
	List<ProductSoldDTO> getProductSoldV2(LocalDate startDate, LocalDate endDate);
	List<ExpenseDTO> getExpenseReort(LocalDate startDate, LocalDate endDate);
	
}
