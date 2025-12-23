package com.kimheng.phoneshop.service;

import java.util.List;

import com.kimheng.phoneshop.dto.SaleDTO;
import com.kimheng.phoneshop.entity.Sale;
import com.kimheng.phoneshop.entity.SaleDetail;

public interface SaleService {
	List<SaleDetail> sale(SaleDTO dto);
	Sale cancelSale(Long saleId);
	Sale getById(Long saleId);
	List<SaleDetail> getSaleDetail(Long saleId);
}
