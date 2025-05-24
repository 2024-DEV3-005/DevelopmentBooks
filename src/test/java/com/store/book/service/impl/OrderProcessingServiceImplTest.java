package com.store.book.service.impl;

import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.TestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static com.store.book.constants.TestConstants.ZERO_PERCENTAGE_OFFER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookStore;
import com.store.book.service.model.OrderPrice;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderProcessingServiceImplTest {

	@Autowired
	OrderProcessingServiceImpl orderProcessingService;

	@Test
	@DisplayName(value = "Should get price as 50.0 for one Book")
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		Book bookOne = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIRST_BOOK);
		Basket basket = new Basket();
		basket.setBooksToOrder(List.of(bookOne));

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THE_BOOK, billedAmount.getTotalPrice()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK, billedAmount.getPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 5% Discount for 2 different books")
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		Book bookOne = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIRST_BOOK);
		Book bookTwo = BookStore.fetchBySerialNo(SERIAL_NO_FOR_SECOND_BOOK);
		Basket basket = new Basket();
		basket.setBooksToOrder(List.of(bookOne, bookTwo));

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_TWO_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS, billedAmount.getPriceAfterDiscount()));
	}
}
