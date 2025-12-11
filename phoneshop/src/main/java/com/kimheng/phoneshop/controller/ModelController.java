package com.kimheng.phoneshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimheng.phoneshop.dto.ModelDTO;
import com.kimheng.phoneshop.mapper.ModelMapper;
import com.kimheng.phoneshop.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/models")
@RequiredArgsConstructor
public class ModelController {
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<?> createBrand(@RequestBody ModelDTO modelDTO){
		return ResponseEntity.ok(modelService.create(modelMapper.toModel(modelDTO)));
	}
}
