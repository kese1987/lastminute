package org.enrico.lastminute.items.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.junit.jupiter.api.Test;

class TaxFreeItemTest {

	@Test
	void testTwoItemPrice() {

		IShoppingCartItem item = new TaxFreeItem("", 2, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.NO_TAX, item.getImportFees());
		assertEquals(AbstractItem.NO_TAX, item.getTaxFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.00", item.getGrossPrice().toString());
		assertEquals("0.00", item.getTaxes().toString());
	}

	@Test
	void testOneItemPrice() {

		IShoppingCartItem item = new TaxFreeItem("", 1, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.NO_TAX, item.getImportFees());
		assertEquals(AbstractItem.NO_TAX, item.getTaxFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.00", item.getGrossPrice().toString());
		assertEquals("0.00", item.getTaxes().toString());
	}
}
