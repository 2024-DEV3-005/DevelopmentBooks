package com.store.book.service.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Basket {

	private List<BookQuantity> booksToOrder;

}
