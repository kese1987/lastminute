package org.enrico.lastminute.items.catalog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.enrico.lastminute.items.IShoppingCartItem;

public abstract class AbstractItem implements IShoppingCartItem {

	public static final BigDecimal STD_10_TAX = BigDecimal.valueOf(0.10);
	public static final BigDecimal STD_5_TAX = BigDecimal.valueOf(0.05);
	public static final BigDecimal NO_TAX = BigDecimal.valueOf(0d);

	private static final BigDecimal SCALE = BigDecimal.valueOf(0.05);

	protected final BigDecimal netPrice;
	protected BigDecimal importDuty = NO_TAX;
	protected BigDecimal tax = STD_10_TAX;
	private int qty;
	private String description;

	public AbstractItem(String description, int qty, BigDecimal netPrice, BigDecimal tax, BigDecimal importDuty) {

		this(description, qty, netPrice, tax);

		Objects.requireNonNull(importDuty);
		this.importDuty = importDuty;

	}

	public AbstractItem(String description, int qty, BigDecimal netPrice, BigDecimal tax) {

		this(description, qty, netPrice);

		Objects.requireNonNull(tax);
		this.tax = tax;

	}

	public AbstractItem(String description, int qty, BigDecimal netPrice) {

		if (qty <= 0) {
			throw new IllegalArgumentException("Quantity must be greather then zero");
		}

		if (!(netPrice != null && netPrice.compareTo(BigDecimal.ZERO) > 0)) {
			throw new IllegalArgumentException("Net price must be greather then zero");
		}
		this.description = StringUtils.isBlank(description) ? "Article" : description;

		this.qty = qty;
		this.netPrice = netPrice.setScale(2, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(qty));
	}

	@Override
	public final BigDecimal getImportFees() {
		return importDuty;
	}

	@Override
	public final BigDecimal getTaxFees() {
		return tax;
	}

	@Override
	public final BigDecimal getNetPrice() {
		return netPrice;
	}

	@Override
	public final BigDecimal getGrossPrice() {
		return netPrice.add(getTaxes()).setScale(2);
	}

	@Override
	public BigDecimal getTaxes() {
		BigDecimal applicableTaxes = getTaxFees().add(getImportFees());
		return taxRound(getNetPrice().multiply(applicableTaxes));
	}

	BigDecimal taxRound(BigDecimal amount) {

		if (amount == null) {
			return null;
		}

		return amount.divide(SCALE).setScale(0, RoundingMode.UP).multiply(SCALE);

	}

	@Override
	public String toString() {
		return qty + " " + description + ": " + getGrossPrice();
	}

}
