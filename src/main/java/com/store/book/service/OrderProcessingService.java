package com.store.book.service;

import com.store.book.service.model.Basket;
import com.store.book.service.model.OrderPrice;

public interface OrderProcessingService {

	OrderPrice getPrice(Basket basket);

}
