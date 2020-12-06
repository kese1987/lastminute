package org.enrico.lastminute.items.catalog;

import java.math.BigDecimal;

public class Item extends AbstractItem {

	public Item(String description, int qty, BigDecimal netPrice, BigDecimal tax) {
		super(description, qty, netPrice, tax);
	}

	public Item(String description, int qty, BigDecimal netPrice) {
		super(description, qty, netPrice);
	}

}
