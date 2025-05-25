package com.store.book.request.validator;

import static com.store.book.constants.AppConstants.EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED;

import org.springframework.util.CollectionUtils;

import com.store.book.exception.EmptyBasketException;
import com.store.book.request.model.ShoppingBasket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShoppingBasketValidator {

	public static void validateBasketNotEmpty(ShoppingBasket shoppingBasket) {
		if (isBasketEmpty(shoppingBasket)) {
			throw new EmptyBasketException(EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED);
		}
	}

	private static boolean isBasketEmpty(ShoppingBasket shoppingBasket) {
		return shoppingBasket == null || CollectionUtils.isEmpty(shoppingBasket.getBooksToOrder());
	}

}
