package com.kimheng.phoneshop.service;

import java.util.List;

import com.kimheng.phoneshop.entity.Model;

public interface ModelService {
	Model create(Model model);
	List<Model> getByBrandId(Integer brandId);
}
