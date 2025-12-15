package com.kimheng.phoneshop.service;

import java.util.Map;

import com.kimheng.phoneshop.dto.PageDTO;
import com.kimheng.phoneshop.entity.Brand;

public interface BrandService {
	Brand Create(Brand brand);
	Brand getById(Long id);
	Brand update(Long id , Brand brandUpdate);
	//List<Brand> getBrands();
	//List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String,String> param);
	//Page<Brand> getBrands(Map<String,String> param);
	PageDTO getBrands(Map<String,String> param);
}
