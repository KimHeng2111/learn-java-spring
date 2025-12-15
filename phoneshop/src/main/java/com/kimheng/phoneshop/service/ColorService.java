package com.kimheng.phoneshop.service;

import com.kimheng.phoneshop.entity.Color;

public interface ColorService {
	Color create(Color color);
	Color getById(Integer colorId);
}
