package com.kimheng.phoneshop.service;

import java.util.List;

import com.kimheng.phoneshop.entity.Brand;

public interface BrandService {
	Brand Create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id , Brand brandUpdate);
	List<Brand> getBrands();
	List<Brand> getBrands(String name);
}
