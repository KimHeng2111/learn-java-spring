package com.kimheng.phoneshop.service;

import com.kimheng.phoneshop.dto.ProductImportDTO;
import com.kimheng.phoneshop.entity.Product;

public interface ProductService {

	Product create(Product product);
	Product getById(Long productId);
	Product importProduct(ProductImportDTO productImportDTO);
}
