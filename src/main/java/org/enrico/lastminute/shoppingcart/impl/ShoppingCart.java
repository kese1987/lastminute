package org.enrico.lastminute.shoppingcart.impl;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.enrico.lastminute.shoppingcart.IShoppingCart;

public class ShoppingCart implements IShoppingCart {

	private List<IShoppingCartItem> items = new LinkedList<>();

	private BigDecimal totalTaxes = BigDecimal.ZERO;
	private BigDecimal totalGrossAmount = BigDecimal.ZERO;

	@Override
	public IShoppingCart addItem(IShoppingCartItem item) {

		if (item == null) {
			throw new IllegalArgumentException("Null item cannot be added");
		}

		items.add(item);
		totalTaxes = totalTaxes.add(item.getTaxes());
		totalGrossAmount = totalGrossAmount.add(item.getGrossPrice());

		return this;
	}

	@Override
	public final BigDecimal getSalesTaxes() {
		return totalTaxes.setScale(2);
	}

	@Override
	public final BigDecimal getTotal() {
		return totalGrossAmount.setScale(2);
	}

	@Override
	public String getReceipt() {

		StringBuilder sb = new StringBuilder();

		for (IShoppingCartItem item : items) {
			sb.append(item.toString()).append("\n");
		}
		sb.append("Sales Taxes: " + getSalesTaxes()).append("\n");
		sb.append("Total: " + getTotal());

		return sb.toString();
	}

}
