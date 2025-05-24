package com.store.book.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPrice {

	Double totalPrice;

	Double priceAfterDiscount;

	Integer discountPercentage;
}
