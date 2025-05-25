package com.store.book.service.impl;

import static com.store.book.constants.AppConstants.MINIMUM_QUANTITY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuantity;
import com.store.book.service.model.OrderSummary;
import com.store.book.service.model.UniqueBookSetPrice;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

	@Override
	public OrderSummary getOrderSummary(Basket basket) {

		return selectBestOrderSummary(generatePotentialOrderSummaries(basket));

	}

	private List<OrderSummary> generatePotentialOrderSummaries(Basket basket) {
		return IntStream.rangeClosed(MINIMUM_QUANTITY, basket.getBooksToOrder().size())
				.mapToObj(maxBooksInGroup -> groupUniqueBookSetsBasedOnQuantity(basket, maxBooksInGroup))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private OrderSummary groupUniqueBookSetsBasedOnQuantity(Basket basket, Integer maxBooksInGroup) {
		List<UniqueBookSetPrice> setOfUniqueBooksWithPrice = new ArrayList<>();
		List<BookQuantity> copyOfBooksToOrder = cloneBookQuantityDetailsList(basket);
		while (!copyOfBooksToOrder.isEmpty()) {
			setOfUniqueBooksWithPrice
					.add(extractUniqueBooksBasedOnAvailableQuantity(copyOfBooksToOrder, maxBooksInGroup));
		}
		return new OrderSummary(setOfUniqueBooksWithPrice);
	}

	private List<BookQuantity> cloneBookQuantityDetailsList(Basket basket) {
		return basket.getBooksToOrder().stream()
				.map(booksInOrder -> new BookQuantity(booksInOrder.getBook(), booksInOrder.getQuantity()))
				.sorted(Comparator.comparing(BookQuantity::getQuantity).reversed()).collect(Collectors.toList());
	}

	private UniqueBookSetPrice extractUniqueBooksBasedOnAvailableQuantity(List<BookQuantity> booksToBeGrouped,
			Integer maxBooksInGroup) {
		Set<Book> uniqueBooks = new HashSet<>();
		Iterator<BookQuantity> iterator = booksToBeGrouped.iterator();

		while (iterator.hasNext() && uniqueBooks.size() <= maxBooksInGroup) {
			BookQuantity book = iterator.next();
			uniqueBooks.add(book.getBook());
			removeBookOrReduceQuantity(iterator, book);
		}
		return new UniqueBookSetPrice(uniqueBooks);
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

	public OrderSummary selectBestOrderSummary(List<OrderSummary> summaries) {
		return Collections.min(summaries, Comparator.comparing(OrderSummary::getFinalPriceAfterDiscount));
	}

}
