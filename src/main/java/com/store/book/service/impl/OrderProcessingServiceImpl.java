package com.store.book.service.impl;

import static com.store.book.constants.AppConstants.BASE_VALUE;
import static com.store.book.constants.AppConstants.FIVE_PERCENTAGE_OFFER;
import static com.store.book.constants.AppConstants.PERCENTAGE_DIVISOR;
import static com.store.book.constants.AppConstants.TEN_PERCENTAGE_OFFER;
import static com.store.book.constants.AppConstants.THREE_BOOKS;
import static com.store.book.constants.AppConstants.TWO_BOOKS;
import static com.store.book.constants.AppConstants.ZERO_PERCENTAGE_OFFER;

import org.springframework.stereotype.Service;

import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.OrderPrice;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

	@Override
	public OrderPrice getPrice(Basket basket) {

		Double orderTotal = calculateTotalPrice(basket);

		if (basket.getBooksToOrder().size() == TWO_BOOKS) {
			Double discountedPrice = computePriceAfterDiscount(orderTotal, FIVE_PERCENTAGE_OFFER);
			return new OrderPrice(orderTotal, discountedPrice, FIVE_PERCENTAGE_OFFER);
		} else if (basket.getBooksToOrder().size() == THREE_BOOKS) {
			Double discountedPrice = computePriceAfterDiscount(orderTotal, TEN_PERCENTAGE_OFFER);
			return new OrderPrice(orderTotal, discountedPrice, TEN_PERCENTAGE_OFFER);
		} else {
			return new OrderPrice(orderTotal, orderTotal, ZERO_PERCENTAGE_OFFER);
		}
	}

	private Double computePriceAfterDiscount(Double orderTotal, int discountPercentage) {
		return orderTotal * (BASE_VALUE - (discountPercentage / PERCENTAGE_DIVISOR));
	}

	private Double calculateTotalPrice(Basket basket) {
		return basket.getBooksToOrder().stream().mapToDouble(Book::getPrice).sum();
	}

}