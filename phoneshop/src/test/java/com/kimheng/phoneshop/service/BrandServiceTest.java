package com.kimheng.phoneshop.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.kimheng.phoneshop.entity.Brand;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.repository.BrandRepository;
import com.kimheng.phoneshop.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	
	@Mock
	private BrandRepository brandRepository;
	private BrandService brandService;
	
	private Brand brand;
	@BeforeEach
	public void setUp() {
		brandService = new BrandServiceImpl(brandRepository);
		
	}
	@Test
	public void testCreateBrand() {
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		//When
		when(brandRepository.save(brand)).thenReturn(brand);
		Brand brandReturn = brandService.Create(brand);
		//Then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
		
	}
	@Test
	public void testGetBrandById() {
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		//When
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand result = brandService.getById(1);
		//Then
		assertEquals("Apple", result.getName());
		assertEquals(1,result.getId());
	}
	@Test
	public void testGetBrandByIdFaild() {
		//Given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		//When
		when(brandRepository.findById(2)).thenReturn(Optional.empty());
		//Then
		assertThatThrownBy(() -> brandService.getById(2))
		.isInstanceOf(ApiException.class)
		.hasMessageContaining("Brand id 2 is not found");
	}
	@Test
	public void testUpdateBrand() {
		//Given
		Brand existing = new Brand();
		existing.setId(1);
		existing.setName("Apple");
		
		Brand updateData = new Brand();
		updateData.setName("Samsung");
		
		when(brandRepository.findById(1)).thenReturn(Optional.of(existing));
		when(brandRepository.save(any(Brand.class))).thenAnswer(inv -> inv.getArgument(0));
		
		//When
		Brand result = brandService.update(1, updateData);
		//Then
		assertEquals("Samsung", result.getName());
		verify(brandRepository).save(existing);
	}
	
}
