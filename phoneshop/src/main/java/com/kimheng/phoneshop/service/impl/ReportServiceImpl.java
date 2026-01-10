package com.kimheng.phoneshop.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.dto.ExpenseDTO;
import com.kimheng.phoneshop.dto.ProductSold;
import com.kimheng.phoneshop.dto.ProductSoldDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.entity.ProductImportHistory;
import com.kimheng.phoneshop.repository.ProductImportRepository;
import com.kimheng.phoneshop.repository.ProductRepository;
import com.kimheng.phoneshop.repository.SaleRepository;
import com.kimheng.phoneshop.service.ReportService;
import com.kimheng.phoneshop.specification.ProductImportHistoryFilter;
import com.kimheng.phoneshop.specification.ProductImportHistorySpec;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final SaleRepository saleRepository;
	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	@Override
	public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return saleRepository.findProductSold(startDate, endDate);
	}
	@Override
	public List<ProductSoldDTO> getProductSoldV2(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return saleRepository.getSalesSummary(startDate, endDate);
	}
	@Override
	public List<ExpenseDTO> getExpenseReort(LocalDate startDate, LocalDate endDate) {
		ProductImportHistoryFilter filter = new ProductImportHistoryFilter();
		filter.setStartDate(startDate);
		filter.setEndDate(endDate);
		ProductImportHistorySpec historySpec = new ProductImportHistorySpec(filter);
		List<ProductImportHistory> proImport = importRepository.findAll(historySpec);
		Set<Long> proId = proImport.stream().map(his -> his.getProduct().getId()).collect(Collectors.toSet());
		List<Product> pros = productRepository.findAllById(proId);
		Map<Long, Product> proMap = pros.stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
		Map<Product, List<ProductImportHistory>> proGroup = proImport.stream().collect(Collectors.groupingBy(pi -> pi.getProduct()));
		var expenseDTOs = new ArrayList<ExpenseDTO>();
		for(var entry : proGroup.entrySet()) {
			Product product = proMap.get(entry.getKey().getId());
			int totalUnit = entry.getValue().stream().mapToInt(his -> his.getImportUnit()).sum();
			double totalPrice = entry.getValue().stream().mapToDouble(his -> his.getPricePerUnit().doubleValue() * his.getImportUnit()).sum();
			ExpenseDTO dto = ExpenseDTO.builder()
					.productName(product.getName())
					.totalUnit(totalUnit)
					.totalAmount(BigDecimal.valueOf(totalPrice))
					.build();
			expenseDTOs.add(dto);
		}
		return expenseDTOs;
	}

}
