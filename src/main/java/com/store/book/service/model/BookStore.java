package com.store.book.service.model;

import static com.store.book.constants.AppConstants.AUTHOR_ROBERT_MARTIN;
import static com.store.book.constants.AppConstants.BOOK_ONE;
import static com.store.book.constants.AppConstants.CLEAN_CODE;
import static com.store.book.constants.AppConstants.PRICE_50;
import static com.store.book.constants.AppConstants.YEAR_2008;

import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookStore {

	private static final Map<String, Book> bookMap = Map.of(BOOK_ONE,
			new Book(CLEAN_CODE, AUTHOR_ROBERT_MARTIN, YEAR_2008, PRICE_50));

	public static Book fetchBySerialNo(String serialNumber) {
		return bookMap.get(serialNumber);
	}

}
