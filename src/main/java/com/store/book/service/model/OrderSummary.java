package com.store.book.service.model;

import java.util.List;

import lombok.Getter;

@Getter
public class OrderSummary {

	List<UniqueBookSetPrice> uniqueBookSetList;
	Double totalPrice;
	Double finalPriceAfterDiscount;

	public OrderSummary(List<UniqueBookSetPrice> uniqueBookSetList) {
		this.uniqueBookSetList = uniqueBookSetList;
		this.totalPrice = calculateTotalPrice();
		this.finalPriceAfterDiscount = computeFinalAmountAfterDiscount();
	}

	private Double calculateTotalPrice() {
		return uniqueBookSetList.stream().mapToDouble(UniqueBookSetPrice::getOrderTotal).sum();
	}

	private Double computeFinalAmountAfterDiscount() {
		return uniqueBookSetList.stream().mapToDouble(UniqueBookSetPrice::getPriceAfterDiscount).sum();
	}

}
