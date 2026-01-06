package com.kimheng.phoneshop.specification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.kimheng.phoneshop.entity.ProductImportHistory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory>{
	private final ProductImportHistoryFilter filter;
	@Override
	public Predicate toPredicate(Root<ProductImportHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		Expression<Date> dateImport = cb.function("date", Date.class, root.get("dateImport"));
		if(Objects.nonNull(filter.getEndDate())) {
			Predicate lessThanOrEqualTo = cb.lessThanOrEqualTo(dateImport, java.sql.Date.valueOf(filter.getEndDate()));
			predicates.add(lessThanOrEqualTo);
		}
		if(Objects.nonNull(filter.getStartDate())) {
			Predicate greaterThanOrEqualTo = cb.greaterThanOrEqualTo(dateImport, java.sql.Date.valueOf(filter.getStartDate()));
			predicates.add(greaterThanOrEqualTo);
		}
		Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
		return predicate;
	}
	
}
