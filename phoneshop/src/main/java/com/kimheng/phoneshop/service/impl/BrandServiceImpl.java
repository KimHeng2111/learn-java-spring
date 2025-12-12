package com.kimheng.phoneshop.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.dto.PageDTO;
import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.repository.BrandRepository;
import com.kimheng.phoneshop.service.BrandService;
import com.kimheng.phoneshop.specification.BrandSpecification;
import com.kimheng.phoneshop.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{

	@Autowired
	private final BrandRepository brandRepository;
	@Override
	public Brand Create(Brand brand) {
		return brandRepository.save(brand);
	}
	@Override
	public Brand getById(Long id) {
		return brandRepository.findById(id).
				orElseThrow(() ->
				new ApiException(HttpStatus.NOT_FOUND,String.format("Brand id %d is not found",id)));
	}
	@Override
	public Brand update(Long id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}
	/*@Override
	public List<Brand> getBrands() {
		List<Brand> brands = brandRepository.findAll();
		return brands;
	}
	@Override
	public List<Brand> getBrands(String name) {
		List<Brand> brands = brandRepository.findByNameLikeIgnoreCase(String.format("%%%s%%", name));
	
		return brands;
	}*/
	/*
	 * @Override public List<Brand> getBrands(Map<String,String> param) {
	 * 
	 * Specification<Brand> spec = BrandSpecification.getAll();
	 * if(param.containsKey("name") && param.get("name") != null) { //return
	 * brandRepository.findByNameLikeIgnoreCase(String.format("%%%s%%",
	 * param.get("name"))); spec =
	 * spec.and(BrandSpecification.containName(param.get("name"))); } return
	 * brandRepository.findAll(spec); }
	 */
	/*
	 * @Override public Page<Brand> getBrands(Map<String, String> param) {
	 * Specification<Brand> spec = BrandSpecification.getAll();
	 * if(param.containsKey("name") && param.get("name") != null) { //return
	 * brandRepository.findByNameLikeIgnoreCase(String.format("%%%s%%",
	 * param.get("name"))); spec =
	 * spec.and(BrandSpecification.containName(param.get("name"))); } int page =
	 * PageUtil.DEFAULT_PAGE_NUMBER; int limit = PageUtil.DEFAULT_PAGE_LIMIT;
	 * if(param.containsKey(PageUtil._PAGE)) { page =
	 * Integer.parseInt(param.get(PageUtil._PAGE)); }
	 * if(param.containsKey(PageUtil._LIMIT)) { limit =
	 * Integer.parseInt(param.get(PageUtil._LIMIT)); } Pageable pageable =
	 * PageUtil.getPageable(page, limit); return
	 * brandRepository.findAll(spec,pageable); }
	 */
	@Override
	public PageDTO getBrands(Map<String, String> param) {
		Specification<Brand> spec = BrandSpecification.getAll();
		if(param.containsKey("name")) {
			spec = spec.and(BrandSpecification.containName(param.get("name")));
		}
		int page = PageUtil.DEFAULT_PAGE_NUMBER;
		if(param.containsKey(PageUtil._PAGE)) {
			page = Integer.parseInt(param.get(PageUtil._PAGE));
		}
		int limit = PageUtil.DEFAULT_PAGE_LIMIT;
		if(param.containsKey(PageUtil._LIMIT)) {
			limit = Integer.parseInt(param.get(PageUtil._LIMIT));
		}
		Pageable pageable = PageUtil.getPageable(page, limit);
		Page<Brand> pages = brandRepository.findAll(spec, pageable);
		PageDTO pageDto = new PageDTO(pages);
		return pageDto;
	}
	

}
 