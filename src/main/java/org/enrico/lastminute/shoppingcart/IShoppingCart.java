package org.enrico.lastminute.shoppingcart;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;

public interface IShoppingCart {

	IShoppingCart addItem(IShoppingCartItem item);

	String getReceipt();

	BigDecimal getSalesTaxes();

	BigDecimal getTotal();

}
