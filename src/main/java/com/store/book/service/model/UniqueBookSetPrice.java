package com.store.book.service.model;

import static com.store.book.constants.AppConstants.BASE_VALUE;
import static com.store.book.constants.AppConstants.PERCENTAGE_DIVISOR;

import java.util.Set;

import lombok.Getter;

@Getter
public class UniqueBookSetPrice {

	private final Set<Book> uniqueBooks;
	private final Integer discountPercentage;
	private final Double orderTotal;
	private final Double priceAfterDiscount;

	public UniqueBookSetPrice(Set<Book> books) {
		this.uniqueBooks = books;
		this.discountPercentage = computeDiscountPercentage();
		this.orderTotal = calculateTotalPrice();
		this.priceAfterDiscount = computePriceAfterDiscount();
	}

	private Integer computeDiscountPercentage() {
		return Discount.findDiscountByNumberOfBooks(uniqueBooks.size());
	}

	private Double calculateTotalPrice() {
		return uniqueBooks.stream().mapToDouble(Book::getPrice).sum();
	}

	private Double computePriceAfterDiscount() {
		return orderTotal * (BASE_VALUE - (discountPercentage / PERCENTAGE_DIVISOR));
	}

}
