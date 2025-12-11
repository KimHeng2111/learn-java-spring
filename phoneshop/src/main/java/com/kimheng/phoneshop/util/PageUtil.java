package com.kimheng.phoneshop.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {

	String _LIMIT = "_limit";
	String _PAGE = "_page";
	int DEFAULT_PAGE_LIMIT = 10;
	int DEFAULT_PAGE_NUMBER = 1;
	
	static Pageable getPageable(int pageNumber, int pageSize) {
		if(pageNumber < DEFAULT_PAGE_NUMBER) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if(pageSize < 1) {
			pageSize = DEFAULT_PAGE_LIMIT;
		}
		return PageRequest.of(pageNumber - 1, pageSize);
	}
}
