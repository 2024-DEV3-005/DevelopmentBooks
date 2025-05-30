package com.store.book.service.impl;

import static com.store.book.constants.TestConstants.FIRST_SET;
import static com.store.book.constants.TestConstants.FOUR_BOOKS_IN_A_SET;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_ELIGIBLE_AND_ONE_NORMAL_BOOK;
import static com.store.book.constants.TestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_SET_OF_FOUR_UNIQUE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_EIGHT_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_FIVE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_FOUR_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.TestConstants.PRICE_FOR_THREE_BOOKS;
import static com.store.book.constants.TestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.TestConstants.QUANTITY_1;
import static com.store.book.constants.TestConstants.QUANTITY_2;
import static com.store.book.constants.TestConstants.SECOND_SET;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIFTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FOURTH_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_THIRD_BOOK;
import static com.store.book.constants.TestConstants.SINGLE_BOOK_IN_A_SET;
import static com.store.book.constants.TestConstants.TWO_BOOKS_IN_A_SET;
import static com.store.book.constants.TestConstants.ZERO_PERCENTAGE_OFFER;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.model.Basket;
import com.store.book.service.model.BookQuantity;
import com.store.book.service.model.BookStore;
import com.store.book.service.model.OrderSummary;

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

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THE_BOOK, orderSummary.getTotalPrice()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK, orderSummary.getFinalPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 5% Discount for 2 different books")
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(
				Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1, SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_TWO_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS, orderSummary.getFinalPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 10% Discount for 3 different books")
	void shouldReturnTenPercentageDiscountForThreeDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1,
				SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1, SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THREE_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_THREE_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS, orderSummary.getFinalPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 20% Discount for 4 different books")
	void shouldReturnTwentyPercentageDiscountForFourDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(
				Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1, SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1,
						SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1, SERIAL_NO_FOR_FOURTH_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FOUR_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS, orderSummary.getFinalPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Should get 25% Discount for 5 different books")
	void shouldReturnTwentyFivePercentageDiscountForFiveDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1,
				SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_1, SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_1, SERIAL_NO_FOR_FOURTH_BOOK,
				QUANTITY_1, SERIAL_NO_FOR_FIFTH_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_FIVE_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_FIVE_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS, orderSummary.getFinalPriceAfterDiscount()));
	}

	@Test
	@DisplayName(value = "Two Quantities of same book not eligible for discount")
	void shouldReturnSamePriceWithOutDiscoutForTwoCopiesOfSameBook() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_2));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_TWO_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(PRICE_FOR_TWO_BOOKS, orderSummary.getFinalPriceAfterDiscount()),
				() -> assertEquals(TWO_BOOKS_IN_A_SET, orderSummary.getUniqueBookSetList().size()),

				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getOrderTotal()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getPriceAfterDiscount()),
				() -> assertEquals(SINGLE_BOOK_IN_A_SET,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getUniqueBooks().size()),
				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getOrderTotal()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getPriceAfterDiscount()),
				() -> assertEquals(SINGLE_BOOK_IN_A_SET,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getUniqueBooks().size()));
	}

	@Test
	@DisplayName(value = "Should get 5% for 2 distinct books and no discount for a repeated book")
	void shouldReturnFivePercentageDiscountForTwoDifferentBooksAndNoDiscountForOneSameBook() {
		List<BookQuantity> bookQtyList = getBookQuantityList(
				Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_1, SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_2));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);
		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(() -> assertEquals(PRICE_FOR_THREE_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_ELIGIBLE_AND_ONE_NORMAL_BOOK,
						orderSummary.getFinalPriceAfterDiscount()),
				() -> assertEquals(TWO_BOOKS_IN_A_SET, orderSummary.getUniqueBookSetList().size()),

				() -> assertEquals(PRICE_FOR_TWO_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getOrderTotal()),
				() -> assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getPriceAfterDiscount()),
				() -> assertEquals(TWO_BOOKS_IN_A_SET,
						orderSummary.getUniqueBookSetList().get(FIRST_SET).getUniqueBooks().size()),

				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getOrderTotal()),
				() -> assertEquals(ZERO_PERCENTAGE_OFFER,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getDiscountPercentage()),
				() -> assertEquals(PRICE_FOR_THE_BOOK,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getPriceAfterDiscount()),
				() -> assertEquals(SINGLE_BOOK_IN_A_SET,
						orderSummary.getUniqueBookSetList().get(SECOND_SET).getUniqueBooks().size()));
	}

	@Test
	@DisplayName(value = "Should get best Discount of 20% for each of the 2 sets of 4 unique books ")
	void shouldGetEachTwentyPercentageDiscountForTwoSetsOfFourDifferentBooks() {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, QUANTITY_2,
				SERIAL_NO_FOR_SECOND_BOOK, QUANTITY_2, SERIAL_NO_FOR_THIRD_BOOK, QUANTITY_2, SERIAL_NO_FOR_FOURTH_BOOK,
				QUANTITY_1, SERIAL_NO_FOR_FIFTH_BOOK, QUANTITY_1));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_SET_OF_FOUR_UNIQUE_BOOKS,
						orderSummary.getFinalPriceAfterDiscount()),
				() -> assertEquals(PRICE_FOR_EIGHT_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(TWO_BOOKS_IN_A_SET, orderSummary.getUniqueBookSetList().size()));

		orderSummary.getUniqueBookSetList()
				.forEach(uniqueBookSet -> assertAll("Verify discounts applied to each unique book set",
						() -> assertEquals(PRICE_FOR_FOUR_BOOKS, uniqueBookSet.getOrderTotal()),
						() -> assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS, uniqueBookSet.getDiscountPercentage()),
						() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS, uniqueBookSet.getPriceAfterDiscount()),
						() -> assertEquals(FOUR_BOOKS_IN_A_SET, uniqueBookSet.getUniqueBooks().size())));
	}

	@ParameterizedTest
	@DisplayName("Should get consistently the Best OrderSummary randomised Orders")
	@CsvSource({ "1,2,2,2,1", "1,1,2,2,2", "1,2,1,2,2", "1,2,2,1,2", "2,1,1,2,2", "2,2,1,2,1", "2,1,2,1,2",
			"2,2,1,1,2" })
	void shouldGetEachTwentyPercentageDiscountForTwoSetsOfFourDifferentBooks(
			int qtyFirstBook, int qtySecondBook, int qtyThirdBook, int qtyFourthBook, int qtyFifthBook) {
		List<BookQuantity> bookQtyList = getBookQuantityList(Map.of(SERIAL_NO_FOR_FIRST_BOOK, qtyFirstBook,
				SERIAL_NO_FOR_SECOND_BOOK, qtySecondBook, SERIAL_NO_FOR_THIRD_BOOK, qtyThirdBook,
				SERIAL_NO_FOR_FOURTH_BOOK, qtyFourthBook, SERIAL_NO_FOR_FIFTH_BOOK, qtyFifthBook));
		Basket basket = new Basket();
		basket.setBooksToOrder(bookQtyList);

		OrderSummary orderSummary = orderProcessingService.getOrderSummary(basket);

		assertAll(
				() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_TWO_SET_OF_FOUR_UNIQUE_BOOKS,
						orderSummary.getFinalPriceAfterDiscount()),
				() -> assertEquals(PRICE_FOR_EIGHT_BOOKS, orderSummary.getTotalPrice()),
				() -> assertEquals(TWO_BOOKS_IN_A_SET, orderSummary.getUniqueBookSetList().size()));

		orderSummary.getUniqueBookSetList()
				.forEach(uniqueBookSet -> assertAll("Verify discounts applied to each unique book set",
						() -> assertEquals(PRICE_FOR_FOUR_BOOKS, uniqueBookSet.getOrderTotal()),
						() -> assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS, uniqueBookSet.getDiscountPercentage()),
						() -> assertEquals(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS, uniqueBookSet.getPriceAfterDiscount()),
						() -> assertEquals(FOUR_BOOKS_IN_A_SET, uniqueBookSet.getUniqueBooks().size())));

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
