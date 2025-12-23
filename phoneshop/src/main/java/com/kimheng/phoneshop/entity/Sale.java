package com.kimheng.phoneshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_sale")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id")
	private long id;
	@Column(name = "date_sold")
	private LocalDateTime dateSold;
	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean status;
}
