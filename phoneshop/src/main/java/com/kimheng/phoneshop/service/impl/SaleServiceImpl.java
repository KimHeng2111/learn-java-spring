package com.kimheng.phoneshop.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.dto.SaleDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.entity.Sale;
import com.kimheng.phoneshop.entity.SaleDetail;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.mapper.SaleMapper;
import com.kimheng.phoneshop.repository.ProductRepository;
import com.kimheng.phoneshop.repository.SaleDetailRepository;
import com.kimheng.phoneshop.repository.SaleRepository;
import com.kimheng.phoneshop.service.ProductService;
import com.kimheng.phoneshop.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

	private final SaleRepository saleRepository;
	private final SaleDetailRepository  saleDetailRepository;
	private final ProductService productService;
	private final SaleMapper saleMapper;
	private final ProductRepository productRepository;
	@Override
	public List<SaleDetail> sale(SaleDTO dto) {
		//Save Sale
		
		Sale saleProduct = saleRepository.save(saleMapper.toSale(dto));
		List<SaleDetail> list = dto.getSolProducts().stream().map(ps -> saleMapper.toSaleDetail(ps, saleProduct)).toList();
		  dto.getSolProducts().stream().forEach(ps -> { 
			  SaleDetail saleDetail =  saleMapper.toSaleDetail(ps, saleProduct);
			  Product product = productService.getById(ps.getProductId());
			  Integer avilableUnit = product.getAvilableUnit();
			  if (avilableUnit < ps.getUnit()) {
				  throw new ApiException(HttpStatus.BAD_REQUEST , "%s is less than your orders !!!".formatted(product.getName()));
			  }
			  product.setAvilableUnit(avilableUnit - ps.getUnit());
			  saleDetailRepository.save(saleDetail); 
			  
		});
		 
		return list;
	}
	@Override
	public Sale cancelSale(Long saleId) {
		Sale sale = getById(saleId);
		long id = sale.getId();
		List<SaleDetail> saleDetail = getSaleDetail(id);
		saleDetail.stream().forEach(sd -> {
			Product product = sd.getProduct();
			System.out.print(product.getName());
			Integer unit = sd.getUnit();
			Integer avilableUnit = unit + product.getAvilableUnit();
			product.setAvilableUnit(avilableUnit);
			productRepository.save(product);
		});
		sale.setStatus(false);
		return saleRepository.save(sale);
	}
	@Override
	public Sale getById(Long saleId) {
		// TODO Auto-generated method stub
		return saleRepository.findById(saleId).orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "SaleId : %d is not found!!!".formatted(saleId)));
	}
	@Override
	public List<SaleDetail> getSaleDetail(Long saleId) {
		List<SaleDetail> list = saleDetailRepository.findBySaleId(saleId).stream().toList();
		System.out.println(list);
		return list;
	}

}
