package com.kimheng.phoneshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.exception.ApiException;
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
	@Override
	public Brand getById(Integer id) {
		return brandRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,String.format("Brand id %d is not found",id)));
	}
	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}
	@Override
	public List<Brand> getBrands() {
		List<Brand> brands = brandRepository.findAll();
		return brands;
	}
	@Override
	public List<Brand> getBrands(String name) {
		List<Brand> brands = brandRepository.findByNameLikeIgnoreCase(String.format("%%%s%%", name));
		return brands;
	}

}
 