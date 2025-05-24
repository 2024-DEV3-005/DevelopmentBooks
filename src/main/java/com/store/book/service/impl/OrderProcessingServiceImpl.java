package com.store.book.service.impl;

import static com.store.book.constants.AppConstants.BASE_VALUE;
import static com.store.book.constants.AppConstants.PERCENTAGE_DIVISOR;

import org.springframework.stereotype.Service;

import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuantity;
import com.store.book.service.model.Discount;
import com.store.book.service.model.OrderPrice;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

	@Override
	public OrderPrice getPrice(Basket basket) {

		Double orderTotal = calculateTotalPrice(basket);

		Integer discount = Discount.findDiscountByNumberOfBooks(basket.getBooksToOrder().size());

		Double discountedPrice = computePriceAfterDiscount(orderTotal, discount);

		return new OrderPrice(orderTotal, discountedPrice, discount);
	}

	private Double computePriceAfterDiscount(Double orderTotal, int discountPercentage) {
		return orderTotal * (BASE_VALUE - (discountPercentage / PERCENTAGE_DIVISOR));
	}

	private Double calculateTotalPrice(Basket basket) {
		return basket.getBooksToOrder().stream().map(BookQuantity::getBook).mapToDouble(Book::getPrice).sum();
	}

}