package com.store.book.response.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSummaryResponse {

	@Schema(description = "Collection of book sets with unique titles")
	List<FinalBookSetPrice> uniqueBookSetList;
	@Schema(example = "200.0", description = "Total price of all the Books Selected to Order")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	Double totalPrice;
	@Schema(example = "150.0", description = "Final price after applying Discount")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	Double finalPriceAfterDiscount;
}
