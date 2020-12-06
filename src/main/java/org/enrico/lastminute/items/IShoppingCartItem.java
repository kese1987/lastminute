package org.enrico.lastminute.items;

import java.math.BigDecimal;

import org.enrico.lastminute.taxes.IImportableGood;
import org.enrico.lastminute.taxes.ITaxableGood;

public interface IShoppingCartItem extends ITaxableGood, IImportableGood {

	BigDecimal getNetPrice();

	BigDecimal getGrossPrice();

	BigDecimal getTaxes();

}
