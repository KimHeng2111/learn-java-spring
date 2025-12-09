 package com.kimheng.phoneshop.util;

import java.util.List;

public class GeneralUtils {
	//convert list of string to integer
	public static List<Integer> toIntegerList(List<String> list){
		List<Integer> list2 = list.stream().map(s -> s.length()).toList();
		return list2;
	}
}
 