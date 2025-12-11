package com.kimheng.phoneshop.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GeneralUtilsTest {
	@Test
	public void testToIntegerList() {
		//Given
		List<String> names = List.of("Dara", "Cheata", "Thida");
		//When
		List<Integer> integerList = GeneralUtils.toIntegerList(names);
		//Then
		//[4,6,5]
		assertEquals(3, integerList.size());
		assertEquals(4,integerList.get(0));
	}
}
