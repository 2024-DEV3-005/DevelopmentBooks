package com.store.book.request.validator;

import static com.store.book.constants.AppConstants.DELIMITER;
import static com.store.book.constants.AppConstants.DUPLICATE_BOOK_MESSAGE;
import static com.store.book.constants.AppConstants.EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED;
import static com.store.book.constants.AppConstants.MINIMUM_QUANTITY;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

import com.store.book.exception.DuplicateBookEntryException;
import com.store.book.exception.EmptyBasketException;
import com.store.book.request.model.SelectedBook;
import com.store.book.request.model.ShoppingBasket;

import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShoppingBasketValidator {

	public static void validateShoppingBasket(ShoppingBasket shoppingBasket) {
		validateBasketNotEmpty(shoppingBasket);
		checkForDuplicateSerialNos(shoppingBasket);
	}

	public static void validateBasketNotEmpty(ShoppingBasket shoppingBasket) {
		if (isBasketEmpty(shoppingBasket)) {
			throw new EmptyBasketException(EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED);
		}
	}

	private static boolean isBasketEmpty(ShoppingBasket shoppingBasket) {
		return shoppingBasket == null || CollectionUtils.isEmpty(shoppingBasket.getBooksToOrder());
	}

	public static void checkForDuplicateSerialNos(ShoppingBasket shoppingBasket) {
		String duplicateEntries = findDuplicateSerialNos(shoppingBasket);
		if (StringUtils.isNotBlank(duplicateEntries)) {
			throw new DuplicateBookEntryException(String.format(DUPLICATE_BOOK_MESSAGE, duplicateEntries));

		}
	}

	private static String findDuplicateSerialNos(ShoppingBasket shoppingBasket) {
		return shoppingBasket.getBooksToOrder().stream().map(SelectedBook::getSerialNumber)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > MINIMUM_QUANTITY).map(Map.Entry::getKey)
				.collect(Collectors.joining(DELIMITER));
	}

}
