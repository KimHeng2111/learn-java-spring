package com.kimheng.phoneshop.service.impl;

import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.repository.ProductRepository;
import com.kimheng.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}

}
