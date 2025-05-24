package com.store.book.service.impl;

import static com.store.book.constants.TestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.TestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderProcessingServiceImplTest {

	@Autowired
	OrderProcessingServiceImpl orderProcessingService;

	@Test
	@DisplayName(value = "Should get price as 50.0 for one Book")
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		assertEquals(PRICE_FOR_THE_BOOK, orderProcessingService.getPrice(SERIAL_NO_FOR_FIRST_BOOK));
	}
}
