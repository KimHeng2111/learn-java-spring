package com.kimheng.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.kimheng.phoneshop.dto.ModelDTO;
import com.kimheng.phoneshop.entity.Model;
import com.kimheng.phoneshop.service.BrandService;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
	ModelEntityMapper INSTANCE = Mappers.getMapper(ModelEntityMapper.class);
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	@Mapping(target = "brandId", source = "model.brand.id")
	ModelDTO toDTO(Model model);
	/*
	 * default Brand map(int brandId) { Brand brand = new Brand();
	 * brand.setId(brandId); return brand; }
	 */

}
