package com.store.book.service.impl;

import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.TestConstants.PRICE_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIFTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FOURTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_THIRD_BOOK;
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
	
	@Test
	@DisplayName(value = "Should get 10% Discount for 3 different books")
	void shouldReturnTenPercentageDiscountForThreeDifferentBooks() {
		Book bookOne = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIRST_BOOK);
		Book bookTwo = BookStore.fetchBySerialNo(SERIAL_NO_FOR_SECOND_BOOK);
		Book bookThree = BookStore.fetchBySerialNo(SERIAL_NO_FOR_THIRD_BOOK);
		Basket basket = new Basket();
		basket.setBooksToOrder(List.of(bookOne, bookTwo, bookThree));

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THREE_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_THREE_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS, billedAmount.getPriceAfterDiscount()));
	}
	
	@Test
	@DisplayName(value = "Should get 20% Discount for 4 different books")
	void shouldReturnTwentyPercentageDiscountForFourDifferentBooks() {
		Book bookOne = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIRST_BOOK);
		Book bookTwo = BookStore.fetchBySerialNo(SERIAL_NO_FOR_SECOND_BOOK);
		Book bookThree = BookStore.fetchBySerialNo(SERIAL_NO_FOR_THIRD_BOOK);
		Book bookFour = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FOURTH_BOOK);
		Basket basket = new Basket();
		basket.setBooksToOrder(List.of(bookOne, bookTwo, bookThree, bookFour));

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FOUR_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS, billedAmount.getPriceAfterDiscount()));
	}
	
	@Test
	@DisplayName(value = "Should get 25% Discount for 5 different books")
	void shouldReturnTwentyFivePercentageDiscountForFiveDifferentBooks() {
		Book bookOne = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIRST_BOOK);
		Book bookTwo = BookStore.fetchBySerialNo(SERIAL_NO_FOR_SECOND_BOOK);
		Book bookThree = BookStore.fetchBySerialNo(SERIAL_NO_FOR_THIRD_BOOK);
		Book bookFour = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FOURTH_BOOK);
		Book bookFive = BookStore.fetchBySerialNo(SERIAL_NO_FOR_FIFTH_BOOK);
		Basket basket = new Basket();
		basket.setBooksToOrder(List.of(bookOne, bookTwo, bookThree, bookFour, bookFive));

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FIVE_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FIVE_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS, billedAmount.getPriceAfterDiscount()));
	}
	
}
