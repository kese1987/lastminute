package org.enrico.lastminute.items.catalog;

import java.math.BigDecimal;

public class ImportedItem extends AbstractItem {

	public ImportedItem(String description, int qty, BigDecimal netPrice) {
		super(description, qty, netPrice, STD_10_TAX, STD_5_TAX);
	}

	public ImportedItem(String description, int qty, BigDecimal netPrice, BigDecimal tax) {
		super(description, qty, netPrice, tax, STD_5_TAX);
	}

	public ImportedItem(String description, int qty, BigDecimal netPrice, BigDecimal tax, BigDecimal importDuty) {
		super(description, qty, netPrice, tax, importDuty);
	}

}
