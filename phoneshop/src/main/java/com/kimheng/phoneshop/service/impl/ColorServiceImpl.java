package com.kimheng.phoneshop.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kimheng.phoneshop.entity.Color;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.repository.ColorRepository;
import com.kimheng.phoneshop.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl  implements ColorService{

	private final ColorRepository repository;
	@Override
	public Color create(Color color) {
		// TODO Auto-generated method stub
		return repository.save(color);
	}

	@Override
	public Color getById(Integer colorId) {
		// TODO Auto-generated method stub
		return repository.findById(colorId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Color id %d is not found!!!!".formatted(colorId)));
	}

}
