package com.store.book.controller;

import static com.store.book.constants.TestConstants.BASKET_WITH_ONE_BOOK;
import static com.store.book.constants.TestConstants.ORDER_SUMMARY_OF_CLIENT_CODE;
import static com.store.book.constants.TestConstants.PRICE_API;
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

}
