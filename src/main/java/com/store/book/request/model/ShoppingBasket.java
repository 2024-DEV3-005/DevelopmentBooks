package com.store.book.request.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShoppingBasket {

	@Schema(description = "Selected books to Order")
	private List<SelectedBook> booksToOrder;

}
