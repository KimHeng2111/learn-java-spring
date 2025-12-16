package com.kimheng.phoneshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.entity.ProductImportHistory;

@Repository
public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Long>{

}
