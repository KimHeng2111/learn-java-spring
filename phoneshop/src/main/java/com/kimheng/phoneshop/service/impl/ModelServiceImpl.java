package com.kimheng.phoneshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.entity.Model;
import com.kimheng.phoneshop.repository.ModelRepository;
import com.kimheng.phoneshop.service.ModelService;

@Service
public class ModelServiceImpl implements ModelService{

	@Autowired
	private ModelRepository modelRepository;
	@Override
	public Model create(Model model) {
		return modelRepository.save(model);
	}
	@Override
	public List<Model> getByBrandId(Integer brandId) {
		
		return modelRepository.findByBrandId(brandId);
	}

}
