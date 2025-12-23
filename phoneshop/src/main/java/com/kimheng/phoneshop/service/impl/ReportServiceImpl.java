package com.kimheng.phoneshop.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.dto.ProductSold;
import com.kimheng.phoneshop.repository.SaleRepository;
import com.kimheng.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final SaleRepository saleRepository;
	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return saleRepository.findProductSold(startDate, endDate);
	}

}
