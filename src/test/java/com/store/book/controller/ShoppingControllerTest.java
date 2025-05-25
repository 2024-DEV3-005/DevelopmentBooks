package com.store.book.controller;

import static com.store.book.constants.TestConstants.BASKET_WITH_ONE_BOOK;
import static com.store.book.constants.TestConstants.DUPLICATE_SERIAL_NUMBERS;
import static com.store.book.constants.TestConstants.EMPTY_BASKET_REQUEST;
import static com.store.book.constants.TestConstants.ERROR_DUPLICATE_QUERY_FOR_SAME_BOOK;
import static com.store.book.constants.TestConstants.ERROR_EMPTY_SHOPPING_BASKET;
import static com.store.book.constants.TestConstants.ERROR_MISSING_ORDER_QUANTITY;
import static com.store.book.constants.TestConstants.ERROR_MISSING_SERIAL_NUMBER;
import static com.store.book.constants.TestConstants.ERROR_NO_BOOK_PRESENT_WITH_GIVEN_SERIAL_NUMBER;
import static com.store.book.constants.TestConstants.ORDER_SUMMARY_OF_CLIENT_CODE;
import static com.store.book.constants.TestConstants.PRICE_API;
import static com.store.book.constants.TestConstants.SELECTED_BOOK_WITH_OUT_SL_NO;
import static com.store.book.constants.TestConstants.SELECTED_BOOK_WITH_ZERO_QUANTITY;
import static com.store.book.constants.TestConstants.UNKNOWN_SERIAL_NUMBER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName(value = "Should get Order summary for single book by serial number from Price API")
	void shouldGetOrderSummaryForSingleBookFromCalculatePriceApi() throws Exception {
		mockMvc.perform(post(PRICE_API).content(BASKET_WITH_ONE_BOOK).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().string(ORDER_SUMMARY_OF_CLIENT_CODE));
	}

	@Test
	@DisplayName(value = "Should Return 'Not Found' error if book is missing for serial number")
	void shouldGet404ErrorWhenBookNotPresentWithGivenSerialNumber() throws Exception {
		mockMvc.perform(post(PRICE_API).content(UNKNOWN_SERIAL_NUMBER).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound())
				.andExpect(content().string(ERROR_NO_BOOK_PRESENT_WITH_GIVEN_SERIAL_NUMBER));
	}

	@Test
	@DisplayName(value = "Should Throw 'Bad request' error for empty basket requests")
	void shouldGet400ResponseWhenEmptyRequestPassedForCalculatePriceApi() throws Exception {
		mockMvc.perform(post(PRICE_API).content(EMPTY_BASKET_REQUEST).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andExpect(content().string(ERROR_EMPTY_SHOPPING_BASKET));
	}

	@Test
	@DisplayName(value = "Should Throw 'Bad request' when duplicate serial number present in the request")
	void shouldGet400ResponseWhenDuplicateSerialNumberPresentInRequest() throws Exception {
		mockMvc.perform(post(PRICE_API).content(DUPLICATE_SERIAL_NUMBERS).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andExpect(content().string(ERROR_DUPLICATE_QUERY_FOR_SAME_BOOK));
	}

	@Test
	@DisplayName(value = "Should Throw 'Bad request' when serial number not present in the request")
	void shouldGet400ResponseWhenSerialNumberNotPresentInSelectedBooksToOrder() throws Exception {
		mockMvc.perform(
				post(PRICE_API).content(SELECTED_BOOK_WITH_OUT_SL_NO).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andExpect(content().string(ERROR_MISSING_SERIAL_NUMBER));
	}

	@Test
	@DisplayName(value = "Should Throw 'Bad request' if Minimum Book Quantity is missing in the request")
	void shouldGet400ResponseWhenQuanityOfBooksIsNotPresent() throws Exception {
		mockMvc.perform(
				post(PRICE_API).content(SELECTED_BOOK_WITH_ZERO_QUANTITY).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andExpect(content().string(ERROR_MISSING_ORDER_QUANTITY));
	}

}
