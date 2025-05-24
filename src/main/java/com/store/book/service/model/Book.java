package com.store.book.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {
	final String title;
	final String authorName;
	final String yearOfPublish;
	final Double price;
}
