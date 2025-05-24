package com.store.book.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookQuantity {
	Book book;
	Integer quantity;
}
