package com.store.book.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {

	public static final Integer ZERO_PERCENTAGE_OFFER = 0;

	public static final Integer OFFER_PERCENTAGE_FOR_TWO_BOOKS = 5;

	public static final Integer OFFER_PERCENTAGE_FOR_THREE_BOOKS = 10;

	public static final Integer OFFER_PERCENTAGE_FOR_FOUR_BOOKS = 20;

	public static final Integer OFFER_PERCENTAGE_FOR_FIVE_BOOKS = 25;

	public static final Integer QUANTITY_1 = 1;

	public static final Integer QUANTITY_2 = 2;

	public static final Integer FIRST_SET = 0;

	public static final Integer SECOND_SET = 1;

	public static final Integer PRICE_FOR_ONE_BOOK = 50;

	public static final Integer SINGLE_BOOK_IN_A_SET = 1;

	public static final Integer TWO_BOOKS_IN_A_SET = 2;

	public static final Integer OFFER_PERCENTAGE_FOR_ONE_BOOK = 0;

	public static final String SERIAL_NO_FOR_FIRST_BOOK = "1";

	public static final String SERIAL_NO_FOR_SECOND_BOOK = "2";

	public static final String SERIAL_NO_FOR_THIRD_BOOK = "3";

	public static final String SERIAL_NO_FOR_FOURTH_BOOK = "4";

	public static final String SERIAL_NO_FOR_FIFTH_BOOK = "5";

	public static final String PRICE_API = "/store/calculatePrice";

	public static final String BASKET_WITH_ONE_BOOK = "{\"booksToOrder\":[{ \"serialNumber\": \"1\",\"quantity\":1}]}";

	public static final String ORDER_SUMMARY_OF_CLIENT_CODE = "{\"uniqueBookSetList\":[{\"uniqueBooks\":[{\"title\":\"Clean Code\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2008\",\"price\":50.0}],\"discountPercentage\":0,\"orderTotal\":50.0,\"priceAfterDiscount\":50.0}],\"totalPrice\":50.0,\"finalPriceAfterDiscount\":50.0}";

	public static final Double PRICE_FOR_THE_BOOK = 50.0;

	public static final Double PRICE_FOR_TWO_BOOKS = 100.0;

	public static final Double PRICE_FOR_THREE_BOOKS = 150.0;

	public static final Double PRICE_FOR_FOUR_BOOKS = 200.0;

	public static final Double PRICE_FOR_FIVE_BOOKS = 250.0;

	public static final Double PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS = 95.0;

	public static final Double PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS = 135.0;

	public static final Double PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS = 160.0;

	public static final Double PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS = 187.5;

	public static final Double PRICE_AFTER_DISCOUNT_FOR_TWO_ELIGIBLE_AND_ONE_NORMAL_BOOK = 145.00;

}
