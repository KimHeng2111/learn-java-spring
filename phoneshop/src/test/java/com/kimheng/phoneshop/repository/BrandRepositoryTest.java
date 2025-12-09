package com.kimheng.phoneshop.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kimheng.phoneshop.entity.Brand;
@DataJpaTest 
public class BrandRepositoryTest {
	@Autowired
	private BrandRepository brandRepository;
	@Test
	public void testFindByNameList() {
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		brandRepository.save(brand);
		//When
		List<Brand> brands = brandRepository.findByNameLikeIgnoreCase("a%");
		//Then
		assertEquals(1, brands.size());
		assertEquals("Apple",brands.get(0).getName());
		assertEquals(1,brands.get(0).getId());
		
	}
}
