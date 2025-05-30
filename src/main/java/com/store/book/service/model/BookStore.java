package com.store.book.service.model;

import static com.store.book.constants.AppConstants.AUTHOR_KENT_BECK;
import static com.store.book.constants.AppConstants.AUTHOR_MICHAEL_C_FEATHERS;
import static com.store.book.constants.AppConstants.AUTHOR_ROBERT_MARTIN;
import static com.store.book.constants.AppConstants.BOOK_FIVE;
import static com.store.book.constants.AppConstants.BOOK_FOUR;
import static com.store.book.constants.AppConstants.BOOK_NOT_PRESENT_TO_PROCESS;
import static com.store.book.constants.AppConstants.BOOK_ONE;
import static com.store.book.constants.AppConstants.BOOK_THREE;
import static com.store.book.constants.AppConstants.BOOK_TWO;
import static com.store.book.constants.AppConstants.CLEAN_ARCHITECTURE;
import static com.store.book.constants.AppConstants.CLEAN_CODE;
import static com.store.book.constants.AppConstants.PRICE_50;
import static com.store.book.constants.AppConstants.TEST_DRIVEN_DEVELOPMENT;
import static com.store.book.constants.AppConstants.THE_CLEAN_CODER;
import static com.store.book.constants.AppConstants.WORKING_EFFECTIVELY_WITH_LEGACY_CODE;
import static com.store.book.constants.AppConstants.YEAR_2003;
import static com.store.book.constants.AppConstants.YEAR_2004;
import static com.store.book.constants.AppConstants.YEAR_2008;
import static com.store.book.constants.AppConstants.YEAR_2011;
import static com.store.book.constants.AppConstants.YEAR_2017;

import java.util.Map;
import java.util.Optional;

import com.store.book.exception.BookNotFoundException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookStore {

	private static final Map<String, Book> bookMap = Map.of(
			BOOK_ONE, new Book(CLEAN_CODE, AUTHOR_ROBERT_MARTIN, YEAR_2008, PRICE_50), 
			BOOK_TWO, new Book(THE_CLEAN_CODER, AUTHOR_ROBERT_MARTIN, YEAR_2011, PRICE_50),
			BOOK_THREE, new Book(CLEAN_ARCHITECTURE, AUTHOR_ROBERT_MARTIN, YEAR_2017, PRICE_50),
			BOOK_FOUR, new Book(TEST_DRIVEN_DEVELOPMENT, AUTHOR_KENT_BECK, YEAR_2003, PRICE_50),
			BOOK_FIVE, new Book(WORKING_EFFECTIVELY_WITH_LEGACY_CODE, AUTHOR_MICHAEL_C_FEATHERS, YEAR_2004, PRICE_50)
			);

	public static Book fetchBySerialNo(String serialNumber) {
		return Optional.ofNullable(bookMap.get(serialNumber))
				.orElseThrow(() -> new BookNotFoundException(String.format(BOOK_NOT_PRESENT_TO_PROCESS, serialNumber)));
	}

}
