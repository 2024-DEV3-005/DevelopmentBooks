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
import static com.store.book.constants.TestConstants.QUANTITY_1;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIFTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FOURTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_THIRD_BOOK;
import static com.store.book.constants.TestConstants.ZERO_PERCENTAGE_OFFER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.model.Basket;
import com.store.book.service.model.BookQuantity;
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
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THE_BOOK, billedAmount.getTotalPrice()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK, billedAmount.getPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 5% Discount for 2 different books")
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(
				Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1, SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_TWO_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS, billedAmount.getPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 10% Discount for 3 different books")
	void shouldReturnTenPercentageDiscountForThreeDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1,
				SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1, SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THREE_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_THREE_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS, billedAmount.getPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 20% Discount for 4 different books")
	void shouldReturnTwentyPercentageDiscountForFourDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(
				Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1, SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1,
						SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1, SERIAL_NO_FOR_FOURTH_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FOUR_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS, billedAmount.getPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 25% Discount for 5 different books")
	void shouldReturnTwentyFivePercentageDiscountForFiveDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1,
				SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1, SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1, SERIAL_NO_FOR_FOURTH_BOOK,
				QUANTITY_1, SERIAL_NO_FOR_FIFTH_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderPrice billedAmount = orderProcessingService.getPrice(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FIVE_BOOKS, billedAmount.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FIVE_BOOKS, billedAmount.getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS, billedAmount.getPriceAfterDiscount()));
	}

	private List<BookQuantity> getBookQuantityList(Map<String, Integer> bookData) {
		return bookData.entrySet().stream()
				.map(book -> createBookWithQuantity(getSerialNumber(book), getQuantity(book))).toList();
	}

	private BookQuantity createBookWithQuantity(String serialNo, Integer quantity) {
		return new BookQuantity(BookStore.fetchBySerialNo(serialNo), quantity);
	}

	private String getSerialNumber(Entry<String, Integer> book) {
		return book.getKey();
	}

	private Integer getQuantity(Entry<String, Integer> book) {
		return book.getValue();
	}

}
