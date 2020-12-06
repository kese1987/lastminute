package org.enrico.lastminute.shoppingcart.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.enrico.lastminute.shoppingcart.IShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ShoppingCartTest {

	private static final String ITEM2_DESCR = "2 Item2: 2.00";
	private static final String ITEM1_DESCR = "1 Item1: 1.00";

	@Mock
	private IShoppingCartItem item1;

	@Mock
	private IShoppingCartItem item2;

	private IShoppingCart shoppingCart;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);

		when(item1.getGrossPrice()).thenReturn(BigDecimal.valueOf(1.10));
		when(item1.getTaxes()).thenReturn(BigDecimal.valueOf(0.10));
		when(item1.toString()).thenReturn(ITEM1_DESCR);

		when(item2.getGrossPrice()).thenReturn(BigDecimal.valueOf(2.20));
		when(item2.getTaxes()).thenReturn(BigDecimal.valueOf(0.20));
		when(item2.toString()).thenReturn(ITEM2_DESCR);

		shoppingCart = new ShoppingCart();
	}

	@Test
	void testNullItemThrowsException() {
		assertThrows(IllegalArgumentException.class, () -> shoppingCart.addItem(null));
	}

	@Test
	void testTotalTaxesAndGrandTotal() {
		shoppingCart.addItem(item1).addItem(item2);

		assertEquals("3.30", shoppingCart.getTotal().toString());
		assertEquals("0.30", shoppingCart.getSalesTaxes().toString());
	}

	@Test
	void testReceiptLayout() {

		shoppingCart.addItem(item1).addItem(item2);

		String expectedReceipt = new StringBuilder().append(ITEM1_DESCR).append("\n").append(ITEM2_DESCR).append("\n")
				.append("Sales Taxes: 0.30\n").append("Total: 3.30").toString();

		assertEquals("3.30", shoppingCart.getTotal().toString());
		assertEquals("0.30", shoppingCart.getSalesTaxes().toString());
		assertEquals(expectedReceipt, shoppingCart.getReceipt());

	}
}
