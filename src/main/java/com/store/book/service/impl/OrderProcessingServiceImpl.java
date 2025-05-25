package com.store.book.service.impl;

import static com.store.book.constants.AppConstants.BASE_VALUE;
import static com.store.book.constants.AppConstants.DEFAULT_DOUBLE;
import static com.store.book.constants.AppConstants.MINIMUM_QUANTITY;
import static com.store.book.constants.AppConstants.PERCENTAGE_DIVISOR;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuantity;
import com.store.book.service.model.Discount;
import com.store.book.service.model.OrderPrice;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

	@Override
	public OrderPrice getPrice(Basket basket) {
		Integer discount = Discount.findDiscountByNumberOfBooks(basket.getBooksToOrder().size());
		List<Set<Book>> listOfCategorizedBookSet = groupUniqueBookSetsBasedOnQuantity(basket);
		return computeFinalPriceAfterDiscount(discount, listOfCategorizedBookSet);
	}

	private List<Set<Book>> groupUniqueBookSetsBasedOnQuantity(Basket basket) {
		List<Set<Book>> setOfuniqueBooks = new ArrayList<>();
		List<BookQuantity> copyOfBooksToOrder = cloneBookQuantityDetailsList(basket);
		while (!copyOfBooksToOrder.isEmpty()) {
			Set<Book> bookSet = extractUniqueBooksBasedOnAvailableQuantity(copyOfBooksToOrder);
			setOfuniqueBooks.add(bookSet);
		}
		return setOfuniqueBooks;
	}

	private List<BookQuantity> cloneBookQuantityDetailsList(Basket basket) {
		return basket.getBooksToOrder().stream()
				.map(shoppingOrder -> new BookQuantity(shoppingOrder.getBook(), shoppingOrder.getQuantity()))
				.collect(Collectors.toList());
	}

	private Set<Book> extractUniqueBooksBasedOnAvailableQuantity(List<BookQuantity> booksToBeGrouped) {
		Set<Book> uniqueBooks = new HashSet<>();
		Integer numberOfUniqueBooks = booksToBeGrouped.size();

		Iterator<BookQuantity> iterator = booksToBeGrouped.iterator();

		while (iterator.hasNext() && uniqueBooks.size() < numberOfUniqueBooks) {
			BookQuantity book = iterator.next();
			uniqueBooks.add(book.getBook());
			removeBookOrReduceQuantity(iterator, book);
		}
		return uniqueBooks;
	}

	private void removeBookOrReduceQuantity(Iterator<BookQuantity> iterator, BookQuantity book) {
		if (isRemainingQtyBelowMinimumQnty(book.getQuantity())) {
			iterator.remove();
		} else {
			book.setQuantity(deductOneFromQuantity(book.getQuantity()));
		}
	}

	private boolean isRemainingQtyBelowMinimumQnty(Integer totalQuantityOfBook) {
		return totalQuantityOfBook <= MINIMUM_QUANTITY;
	}

	private int deductOneFromQuantity(Integer totalQuantityOfBook) {
		return totalQuantityOfBook - MINIMUM_QUANTITY;
	}

	private OrderPrice computeFinalPriceAfterDiscount(Integer discount, List<Set<Book>> listOfCategorizedBookSet) {
		Double orderTotal = DEFAULT_DOUBLE;
		Double discountedPrice = DEFAULT_DOUBLE;
		for (Set<Book> bookSet : listOfCategorizedBookSet) {
			Double totalPriceOfSet = calculateTotalPrice(bookSet);
			orderTotal += totalPriceOfSet;
			discountedPrice += computePriceAfterDiscount(totalPriceOfSet,
					Discount.findDiscountByNumberOfBooks(bookSet.size()));
		}
		return new OrderPrice(orderTotal, discountedPrice, discount);
	}

	private Double computePriceAfterDiscount(Double orderTotal, int discountPercentage) {
		return orderTotal * (BASE_VALUE - (discountPercentage / PERCENTAGE_DIVISOR));
	}

	private Double calculateTotalPrice(Set<Book> bookSet) {
		return bookSet.stream().mapToDouble(Book::getPrice).sum();
	}

}
