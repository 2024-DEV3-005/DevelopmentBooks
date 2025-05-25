package com.store.book.response.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalBookSetPrice {

	@Schema(description = "List of books, each with a unique title")
	private List<BookInfo> uniqueBooks;
	@Schema(example = "20", description = "Discount applicable to the books in the list")
	private Integer discountPercentage;
	@Schema(example = "200.0", description = "Total price of all books in the list.")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Double orderTotal;
	@Schema(example = "160.0", description = "Final price of all books in the list after discount")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Double priceAfterDiscount;

}
