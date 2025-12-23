package com.kimheng.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.SaleDTO;
import com.kimheng.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sale")
@RequiredArgsConstructor
public class SaleController {
	private final SaleService saleService;
	@PostMapping
	public ResponseEntity<?> saleProduct(@RequestBody SaleDTO sale){
		return ResponseEntity.ok(saleService.sale(sale));
	}
	@PutMapping("{saleId}/cancel")
	public ResponseEntity<?> cancelSale(@PathVariable Long saleId){
		return ResponseEntity.ok(saleService.cancelSale(saleId));
	}
}
