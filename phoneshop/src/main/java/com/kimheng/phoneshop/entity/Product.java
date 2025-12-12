package com.kimheng.phoneshop.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "image_path")
	private String imagePath;
	private Integer avilableUnit;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	@Column(name = "sale_price")
	private BigDecimal salePrice;
}
