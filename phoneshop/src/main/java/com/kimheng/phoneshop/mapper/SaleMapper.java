package com.kimheng.phoneshop.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kimheng.phoneshop.dto.SaleDTO;
import com.kimheng.phoneshop.dto.SoldProduct;
import com.kimheng.phoneshop.entity.Sale;
import com.kimheng.phoneshop.entity.SaleDetail;
import com.kimheng.phoneshop.service.ProductService;

@Mapper(componentModel = "spring", uses = {ProductService.class,})
public interface SaleMapper {
	Sale toSale(SaleDTO saleDTO);
	@Mapping(target = "product", source = "dto.productId")
    @Mapping(target = "sale", source = "sale")
    @Mapping(
        target = "amount",
        expression = "java(SaleMapper.toBigDecimal(dto.getUnit()).multiply(saleDetail.getProduct().getSalePrice()))"
    )
    @Mapping(target = "id", ignore = true)
    SaleDetail toSaleDetail(SoldProduct dto, Sale sale);

    static BigDecimal toBigDecimal(Integer unit) {
        return unit == null ? BigDecimal.ZERO : BigDecimal.valueOf(unit);
    }

}