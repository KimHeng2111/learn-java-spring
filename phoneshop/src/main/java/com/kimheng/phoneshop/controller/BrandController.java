package com.kimheng.phoneshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.BrandDTO;
import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.service.BrandService;
import com.kimheng.phoneshop.util.Mapper;

@RestController
@RequestMapping("brands")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		Brand brand = Mapper.toBrand(brandDTO);
		brand = brandService.Create(brand);
		return ResponseEntity.ok(brand);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(Mapper.toDTO(brand));
	}
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer brandId , @RequestBody Brand brandDTO){
		
		Brand brand = brandService.update(brandId,brandDTO);
		return ResponseEntity.ok(brand);
	}
}
