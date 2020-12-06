package org.enrico.lastminute.items.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.junit.jupiter.api.Test;

class ImportedItemTest {

	@Test
	void testTwoImportedItemDefaults() {

		IShoppingCartItem item = new ImportedItem("", 2, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.STD_10_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_5_TAX, item.getImportFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.60", item.getGrossPrice().toString());
		assertEquals("0.60", item.getTaxes().toString());

	}

	@Test
	void testTwoImportedItemCustomTax() {

		IShoppingCartItem item = new ImportedItem("", 2, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_5_TAX, item.getImportFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.40", item.getGrossPrice().toString());
		assertEquals("0.40", item.getTaxes().toString());

	}

	@Test
	void testTwoImportedItemCustomTaxAndImportFee() {

		IShoppingCartItem item = new ImportedItem("", 2, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX, AbstractItem.STD_10_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_10_TAX, item.getImportFees());

		assertEquals("4.00", item.getNetPrice().toString());
		assertEquals("4.60", item.getGrossPrice().toString());
		assertEquals("0.60", item.getTaxes().toString());

	}

	@Test
	void testImportedItemDefaults() {

		IShoppingCartItem item = new ImportedItem("", 1, BigDecimal.valueOf(2d));

		assertEquals(AbstractItem.STD_10_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_5_TAX, item.getImportFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.30", item.getGrossPrice().toString());
		assertEquals("0.30", item.getTaxes().toString());

	}

	@Test
	void testImportedItemCustomTax() {

		IShoppingCartItem item = new ImportedItem("", 1, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_5_TAX, item.getImportFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.20", item.getGrossPrice().toString());
		assertEquals("0.20", item.getTaxes().toString());

	}

	@Test
	void testImportedItemCustomTaxAndImportFee() {

		IShoppingCartItem item = new ImportedItem("", 1, BigDecimal.valueOf(2d), AbstractItem.STD_5_TAX, AbstractItem.STD_10_TAX);

		assertEquals(AbstractItem.STD_5_TAX, item.getTaxFees());
		assertEquals(AbstractItem.STD_10_TAX, item.getImportFees());

		assertEquals("2.00", item.getNetPrice().toString());
		assertEquals("2.30", item.getGrossPrice().toString());
		assertEquals("0.30", item.getTaxes().toString());

	}

}
