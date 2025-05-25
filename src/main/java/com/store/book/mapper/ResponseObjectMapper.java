package com.store.book.mapper;

import static com.store.book.request.validator.ShoppingBasketValidator.validateShoppingBasket;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.store.book.request.model.SelectedBook;
import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.model.Basket;
import com.store.book.service.model.BookQuantity;
import com.store.book.service.model.BookStore;

@Mapper(componentModel = "spring")
public interface ResponseObjectMapper {

	default Basket toBasket(ShoppingBasket source) {
		validateShoppingBasket(source);
		Basket basket = new Basket();
		List<BookQuantity> booksToOrder = new ArrayList<>();

		for (SelectedBook order : source.getBooksToOrder()) {
			booksToOrder.add(new BookQuantity(BookStore.fetchBySerialNo(order.getSerialNumber()), order.getQuantity()));
		}
		basket.setBooksToOrder(booksToOrder);
		return basket;
	}

}
