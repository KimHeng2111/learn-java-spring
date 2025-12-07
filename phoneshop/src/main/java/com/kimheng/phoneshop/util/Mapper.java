package com.kimheng.phoneshop.util;

import com.kimheng.phoneshop.dto.BrandDTO;
import com.kimheng.phoneshop.entity.Brand;

public class Mapper {
	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
		brand.setName(dto.getName());
		return brand;
	}
	 public static BrandDTO toDTO(Brand brand) {
		 BrandDTO dto = new BrandDTO();
		 dto.setName(brand.getName());
		 return dto;
	 }
}
