package com.store.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.book.mapper.ResponseObjectMapper;
import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.OrderSummary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/store")
public class ShoppingController {

	private final OrderProcessingService orderProcessingService;

	private final ResponseObjectMapper mapper;

	public ShoppingController(OrderProcessingService orderProcessingService, ResponseObjectMapper mapper) {
		this.orderProcessingService = orderProcessingService;
		this.mapper = mapper;
	}

	@Operation(summary = "Fetch the price for books ", description = "Fetch the best price for the developement books ")
	@ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successfully fetched the Price for the book"))
	@PostMapping(value = "calculatePrice", produces = "application/json")
	public ResponseEntity<OrderSummary> fetchCalculatePrice(@RequestBody ShoppingBasket basket) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(orderProcessingService.getOrderSummary(mapper.toBasket(basket)));
	}
}
