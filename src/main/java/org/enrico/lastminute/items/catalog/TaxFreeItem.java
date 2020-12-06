package org.enrico.lastminute.items.catalog;

import java.math.BigDecimal;

public class TaxFreeItem extends AbstractItem {

	public TaxFreeItem(String description, int qty, BigDecimal netPrice) {
		super(description, qty, netPrice, NO_TAX);
	}

}
