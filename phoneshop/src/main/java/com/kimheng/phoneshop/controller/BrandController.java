package com.kimheng.phoneshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.BrandDTO;
import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.mapper.BrandMapper;
import com.kimheng.phoneshop.mapper.ModelEntityMapper;
import com.kimheng.phoneshop.service.BrandService;
import com.kimheng.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("brands")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class BrandController {
	
	
	private final BrandService brandService;
	private final ModelService modelService;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.Create(brand);
		return ResponseEntity.ok(brand);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
	}
	/*
	 * @GetMapping() public ResponseEntity<?> getBrands(){ return
	 * ResponseEntity.ok(brandService.getBrands().stream().map((b) ->
	 * BrandMapper.INSTANCE.toBrandDTO(b))); //return
	 * ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand)); }
	 */
	
	@GetMapping()
	public ResponseEntity<?> getBrands(@RequestParam Map<String,String> param){
		
		return ResponseEntity.ok(brandService.getBrands(param));
	}
	@GetMapping("{id}/models")
	public ResponseEntity<?> getModels(@PathVariable("id") Integer brandId){
		return ResponseEntity.ok(modelService.getByBrandId(brandId).stream().map(m -> ModelEntityMapper.INSTANCE.toDTO(m)));
	}
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer brandId , @RequestBody Brand brandDTO){
		
		Brand brand = brandService.update(brandId,brandDTO);
		return ResponseEntity.ok(brand);
	}
	
}
