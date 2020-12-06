package org.enrico.lastminute.items.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.junit.jupiter.api.Test;

class ItemTest {

	private IShoppingCartItem item;

	@Test
	void testTwoItemPriceDefaultTax() {

		item = new Item("", 2, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.STD_10_TAX, item.getTaxFees());
		assertEquals(AbstractItem.NO_TAX, item.getImportFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.40", item.getGrossPrice().toString());
		assertEquals("0.40", item.getTaxes().toString());
	}

	@Test
	void testTwoItemPriceCustomTax() {

		item = new Item("", 2, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.NO_TAX, item.getImportFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.20", item.getGrossPrice().toString());
		assertEquals("0.20", item.getTaxes().toString());
	}

	@Test
	void testItemPriceDefaultTax() {

		item = new Item("", 1, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.STD_10_TAX, item.getTaxFees());
		assertEquals(AbstractItem.NO_TAX, item.getImportFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.20", item.getGrossPrice().toString());
		assertEquals("0.20", item.getTaxes().toString());
	}

	@Test
	void testItemPriceCustomTax() {

		item = new Item("", 1, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.NO_TAX, item.getImportFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.10", item.getGrossPrice().toString());
		assertEquals("0.10", item.getTaxes().toString());
	}
}
