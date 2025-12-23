package com.kimheng.phoneshop.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kimheng.phoneshop.dto.PriceDTO;
import com.kimheng.phoneshop.dto.ProductImportDTO;
import com.kimheng.phoneshop.entity.Product;
import com.kimheng.phoneshop.entity.ProductImportHistory;
import com.kimheng.phoneshop.exception.ApiException;
import com.kimheng.phoneshop.mapper.ProductMapper;
import com.kimheng.phoneshop.repository.ProductImportRepository;
import com.kimheng.phoneshop.repository.ProductRepository;
import com.kimheng.phoneshop.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	private final ProductImportRepository importRepository;
	private final ProductMapper mapper;

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}

	@Override
	public Product getById(Long productId) {
		return repository.findById(productId).orElseThrow(
				() -> new ApiException(HttpStatus.NOT_FOUND, "Product id: %d is Not Found !!!".formatted(productId)));
	}

	@Override
	public Product importProduct(ProductImportDTO productImportDTO) {
		// Get Product To Save
		Product product = this.getById(productImportDTO.getProductId());
		// Check avilableUnit null or not null
		if (product.getAvilableUnit() != null) {
			product.setAvilableUnit(product.getAvilableUnit() + productImportDTO.getImportUnit());
		} else {
			// update avilableUnit when null
			product.setAvilableUnit(productImportDTO.getImportUnit());
		}

		repository.save(product);
		ProductImportHistory productImport = mapper.toProductImport(productImportDTO, product);
		importRepository.save(productImport);
		return product;

	}

	@Override
	public void setPrice(Long productId, PriceDTO price) {
		Product product = getById(productId);
		product.setSalePrice(price.getPrice());
		repository.save(product);
	}

	@Override
	public Map<Integer, String> upload(MultipartFile file) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				rowIterator.next(); // skip header row
			}
			int rowNumber = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				rowNumber += 1;
				try {
					ProductImportDTO importDTO = getImportDTO(row);
					importProduct(importDTO);
					map.put(rowNumber, "Import Complete");
				}catch (Exception e){
					map.put(rowNumber, "ERROR" +  e.getMessage());
				}
			}
			
		} catch (Exception e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return map;
	}

	private Product getProductByModelIdAndColorId(Long modelId, Integer colorId) {
		Product product = repository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST,
						"Product wiht ModelID : %d  and ColorID : %d has not found!!!".formatted(modelId, colorId)));
		return product;
	}

	private ProductImportDTO getImportDTO(Row row) {
		Cell cellModelId = row.getCell(0);
		Long modelId = (long) cellModelId.getNumericCellValue();
		Integer colorId = (int) row.getCell(1).getNumericCellValue();
		BigDecimal importPrice = BigDecimal.valueOf(row.getCell(2).getNumericCellValue());
		Integer importUnit = (int) row.getCell(3).getNumericCellValue();
		LocalDateTime importDate = row.getCell(4).getLocalDateTimeCellValue();
		Product product = getProductByModelIdAndColorId(modelId, colorId);
		ProductImportDTO dto = new ProductImportDTO(product.getId(), importUnit, importPrice, importDate);
		System.out.print(dto);
		return dto;
	}

}
