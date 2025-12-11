package com.kimheng.phoneshop.dto;


import java.util.List;

import org.springframework.data.domain.Page;


import lombok.Data;

@Data
public class PageDTO {
	private List<?> list;
	private PaginationDTO pagination;
	public PageDTO(Page<?> page) {
		this.list = page.getContent();
		this.pagination = PaginationDTO.builder()
				.first(page.isFirst())
				.last(page.isLast())
				.empty(page.isEmpty())
				.pageSize(page.getSize())
				.pageNumber(page.getNumber())
				.totalPages(page.getTotalPages())
				.totalElements(page.getTotalElements())
				.numberOfElements(page.getNumberOfElements())
				.build();
		
	}
}
