package com.store.book.service.impl;

import org.springframework.stereotype.Service;

import com.store.book.service.OrderProcessingService;
import com.store.book.service.model.BookStore;

@Service
public class OrderProcessingServiceImpl implements OrderProcessingService {

		@Override
		public Double getPrice(String serialNumber) {
			
			return BookStore.fetchBySerialNo(serialNumber).getPrice();
		}
}
