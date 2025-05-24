package com.store.book.service.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Discount {

	DEFAULT(1, 0), TWO(2, 5), THREE(3, 10), FOUR(4, 20), FIVE(5, 25);

	private static final Map<Integer, Integer> discountMap = Stream.of(values()).collect(
			Collectors.toUnmodifiableMap(Discount::getNumberOfBooks, Discount::getDiscountPercentage));

	final Integer numberOfBooks;
	final Integer discountPercentage;

	public static Integer findDiscountByNumberOfBooks(Integer numberOfBooks) {
		return discountMap.getOrDefault(numberOfBooks, DEFAULT.getDiscountPercentage());
	}

}
