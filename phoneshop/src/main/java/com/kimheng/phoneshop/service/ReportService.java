package com.kimheng.phoneshop.service;

import java.time.LocalDate;
import java.util.List;

import com.kimheng.phoneshop.dto.ProductSold;

public interface ReportService {
	List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
}
