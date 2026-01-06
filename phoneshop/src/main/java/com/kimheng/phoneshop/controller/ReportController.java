package com.kimheng.phoneshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.ExpenseDTO;
import com.kimheng.phoneshop.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ReportController {
	private final ReportService reportService;
	
	@GetMapping("odl/{startDate}/{endDate}")
	public ResponseEntity<?> getProductSoldReport(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate ,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		return ResponseEntity.ok(reportService.getProductSold(startDate, endDate));
	}
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> getProductSoldReportV2(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate ,
			@PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		return ResponseEntity.ok(reportService.getProductSoldV2(startDate, endDate));
	}
	@GetMapping("expense")
	public ResponseEntity<?> getExpenseReport(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate
											, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		System.out.println(startDate);
		List<ExpenseDTO> expenseReort = reportService.getExpenseReort(startDate, endDate);
		
		return ResponseEntity.ok(expenseReort);
	}
}
