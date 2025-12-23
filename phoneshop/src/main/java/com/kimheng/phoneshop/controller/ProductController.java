package com.kimheng.phoneshop.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kimheng.phoneshop.dto.PriceDTO;
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
	@PutMapping("/{productId}/set-sale-price")
	public ResponseEntity<?> setSoldPrice(@PathVariable Long productId,@RequestBody @Valid  PriceDTO price){
		service.setPrice(productId, price);
		 return ResponseEntity.ok().build();
	}
	@PostMapping("upload")
	public ResponseEntity<?> uploadProduct(@RequestParam MultipartFile file){
		Map<Integer, String> upload = service.upload(file);
		return ResponseEntity.ok(upload);
	}
}
