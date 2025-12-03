package com.kimheng.phoneshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.repository.BrandRepository;
import com.kimheng.phoneshop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandRepository brandRepository;
	@Override
	public Brand Create(Brand brand) {
		return brandRepository.save(brand);
	}

}
