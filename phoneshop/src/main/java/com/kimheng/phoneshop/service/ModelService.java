package com.kimheng.phoneshop.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kimheng.phoneshop.entity.Model;

public interface ModelService {
	@PreAuthorize("hasAuthority('MODEL_WRITE')")
	Model create(Model model);
	@PreAuthorize("hasAuthority('MODEL_READ')")
	List<Model> getByBrandId(Long brandId);
	@PreAuthorize("hasAuthority('MODEL_READ')")
	Model getById(Long id);
}
