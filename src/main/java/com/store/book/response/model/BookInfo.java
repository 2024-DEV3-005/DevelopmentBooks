package com.store.book.response.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Book information model")
public class BookInfo {

	@Schema(example = "Book Title", description = "Title of the book")
	private String title;

	@Schema(example = "Author Name", description = "Name of the author")
	private String authorName;

	@Schema(example = "1789", description = "Year of publication")
	private String yearOfPublish;

	@Schema(example = "50.00", description = "Price of the book")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Double price;

}
