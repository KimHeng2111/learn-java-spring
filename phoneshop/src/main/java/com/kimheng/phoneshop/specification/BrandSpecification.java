package com.kimheng.phoneshop.specification;

import org.springframework.data.jpa.domain.Specification;

import com.kimheng.phoneshop.entity.Brand;


public class BrandSpecification {
	public static Specification<Brand> getAll(){
		return (root, query, cb) -> cb.like(root.get("name"), "%" );
	}
	public static Specification<Brand> containName(String name){
		return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
	}
}
