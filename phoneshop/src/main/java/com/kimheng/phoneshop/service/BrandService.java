package com.kimheng.phoneshop.service;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kimheng.phoneshop.dto.PageDTO;
import com.kimheng.phoneshop.entity.Brand;

public interface BrandService {
	@PreAuthorize("hasAuthority('BRAND_WRITE')")
	Brand Create(Brand brand);
	@PreAuthorize("hasAuthority('BRAND_READ')")
	Brand getById(Long id);
	@PreAuthorize("hasAuthority('BRAND_WRITE')")
	Brand update(Long id , Brand brandUpdate);
	@PreAuthorize("hasAuthority('BRAND_READ')")
	//List<Brand> getBrands();
	//List<Brand> getBrands(String name);
	//List<Brand> getBrands(Map<String,String> param);
	//Page<Brand> getBrands(Map<String,String> param);
	PageDTO getBrands(Map<String,String> param);
}
