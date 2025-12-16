package com.kimheng.phoneshop.controller;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.ProductDTO;
import com.kimheng.phoneshop.dto.ProductImportDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.mapper.ProductMapper;
import com.kimheng.phoneshop.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService service;
	private final ProductMapper productMapper;
	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto){
		Product product = productMapper.toProduct(dto);
		return ResponseEntity.ok(service.create(product));
	}
	@PostMapping("imports")
	public ResponseEntity<?> importProduct(@RequestBody @Valid @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") ProductImportDTO dto){
		if (dto.getDateImport() == null) {
			dto.setDateImport(LocalDateTime.now());
		}
		Product product = service.importProduct(dto);
		return ResponseEntity.ok(product);
	}
}
