package com.kimheng.phoneshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kimheng.phoneshop.dto.ProductSold;
import com.kimheng.phoneshop.dto.ProductSoldDTO;
import com.kimheng.phoneshop.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{
	@Query(value = """
	        SELECT p.product_id AS productId,
	               p.product_name AS productName,
	               SUM(sd.unit) AS qty,
	               SUM(sd.amount) AS totalAmount
	        FROM tbl_sale s
	        INNER JOIN tbl_sale_detail sd ON s.sale_id = sd.sale_id
	        INNER JOIN tbl_product p ON p.product_id = sd.product_id
	        WHERE DATE(s.date_sold) >= :startDate
	          AND DATE(s.date_sold) <= :endDate
	          AND s.status = true
	        GROUP BY p.product_id, p.product_name
	        """, nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate, LocalDate endDate);
	
	@Query("SELECT new com.kimheng.phoneshop.dto.ProductSoldDTO(" +
		       "p.id, p.name, SUM(sd.unit), SUM(sd.amount)) " +
		       "FROM SaleDetail sd JOIN sd.sale s JOIN sd.product p " +
		       "WHERE FUNCTION('DATE', s.dateSold) BETWEEN :startDate AND :endDate AND s.status = true " +
		       "GROUP BY p.id, p.name")
		List<ProductSoldDTO> getSalesSummary(@Param("startDate") LocalDate startDate,
		                                     @Param("endDate") LocalDate endDate);

}
