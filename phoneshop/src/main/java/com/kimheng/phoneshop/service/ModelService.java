package com.kimheng.phoneshop.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kimheng.phoneshop.entity.Model;

public interface ModelService {
	@PreAuthorize("hasRole('ADMIN')")
	Model create(Model model);
	@PreAuthorize("hasRole('SALE')")
	List<Model> getByBrandId(Long brandId);
	Model getById(Long id);
}
