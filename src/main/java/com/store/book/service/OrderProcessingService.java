package com.store.book.service;

import com.store.book.service.model.Basket;
import com.store.book.service.model.OrderSummary;

public interface OrderProcessingService {

	OrderSummary getOrderSummary(Basket basket);

}
