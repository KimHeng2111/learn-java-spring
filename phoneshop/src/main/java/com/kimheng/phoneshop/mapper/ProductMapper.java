package com.kimheng.phoneshop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kimheng.phoneshop.dto.ProductDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.service.ColorService;
import com.kimheng.phoneshop.service.ModelService;

@Mapper(componentModel = "spring",uses = {ColorService.class,ModelService.class})
public interface ProductMapper {
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	@Mapping(target = "name", expression = "java(product.getModel().getName() + \" \" + product.getColor().getName())")
	Product toProduct(ProductDTO productDTO);
}
