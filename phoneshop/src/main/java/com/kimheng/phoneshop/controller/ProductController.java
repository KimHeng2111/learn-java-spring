package com.kimheng.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.ProductDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.mapper.ProductMapper;
import com.kimheng.phoneshop.service.ProductService;

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
}
