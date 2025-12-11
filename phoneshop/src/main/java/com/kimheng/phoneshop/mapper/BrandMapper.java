package com.kimheng.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kimheng.phoneshop.dto.BrandDTO;
import com.kimheng.phoneshop.entity.Brand;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	Brand toBrand(BrandDTO dto);
	BrandDTO toBrandDTO(Brand brand);
}
