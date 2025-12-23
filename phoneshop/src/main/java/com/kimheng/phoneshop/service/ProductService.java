package com.kimheng.phoneshop.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kimheng.phoneshop.dto.PriceDTO;
import com.kimheng.phoneshop.dto.ProductImportDTO;
import com.kimheng.phoneshop.entity.Product;

public interface ProductService {

	Product create(Product product);
	Product getById(Long productId);
	Product importProduct(ProductImportDTO productImportDTO);
	void setPrice(Long productId , PriceDTO	price);
	Map<Integer,String> upload(MultipartFile file);
}
