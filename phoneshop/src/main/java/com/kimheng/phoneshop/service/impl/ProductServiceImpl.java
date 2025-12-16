package com.kimheng.phoneshop.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.dto.ProductImportDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.entity.ProductImportHistory;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.mapper.ProductMapper;
import com.kimheng.phoneshop.repository.ProductImportRepository;
import com.kimheng.phoneshop.repository.ProductRepository;
import com.kimheng.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	private final ProductImportRepository importRepository;
	private final ProductMapper mapper;
	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}
	@Override
	public Product getById(Long productId) {
		return repository.findById(productId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Product id: %d is Not Found !!!".formatted(productId)));
	}
	@Override
	public Product importProduct(ProductImportDTO productImportDTO) {
		//Get Product To Save
		Product product = this.getById(productImportDTO.getProductId());
		//Check avilableUnit null or not null
		if(product.getAvilableUnit() != null) {
			product.setAvilableUnit(product.getAvilableUnit() + productImportDTO.getImportUnit());
		}else {
			//update avilableUnit when null
			product.setAvilableUnit(productImportDTO.getImportUnit());
		}
		
		repository.save(product);
		ProductImportHistory productImport = mapper.toProductImport(productImportDTO, product);
		importRepository.save(productImport);
		return product;
		
	}
	

}
