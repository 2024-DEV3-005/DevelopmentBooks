package com.store.book.service.model;

import static com.store.book.constants.AppConstants.AUTHOR_ROBERT_MARTIN;
import static com.store.book.constants.AppConstants.BOOK_ONE;
import static com.store.book.constants.AppConstants.BOOK_THREE;
import static com.store.book.constants.AppConstants.BOOK_TWO;
import static com.store.book.constants.AppConstants.CLEAN_ARCHITECTURE;
import static com.store.book.constants.AppConstants.CLEAN_CODE;
import static com.store.book.constants.AppConstants.PRICE_50;
import static com.store.book.constants.AppConstants.THE_CLEAN_CODER;
import static com.store.book.constants.AppConstants.YEAR_2008;
import static com.store.book.constants.AppConstants.YEAR_2011;
import static com.store.book.constants.AppConstants.YEAR_2017;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookStore {

	private static final Map<String, Book> bookMap = Map.of(
			BOOK_ONE, new Book(CLEAN_CODE, AUTHOR_ROBERT_MARTIN, YEAR_2008, PRICE_50), 
			BOOK_TWO, new Book(THE_CLEAN_CODER, AUTHOR_ROBERT_MARTIN, YEAR_2011, PRICE_50),
			BOOK_THREE, new Book(CLEAN_ARCHITECTURE, AUTHOR_ROBERT_MARTIN, YEAR_2017, PRICE_50)
			);

	public static Book fetchBySerialNo(String serialNumber) {
		return bookMap.get(serialNumber);
	}

}
