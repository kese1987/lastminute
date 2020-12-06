package org.enrico.lastminute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.enrico.lastminute.items.IShoppingCartItem;
import org.enrico.lastminute.items.catalog.AbstractItem;
import org.enrico.lastminute.items.catalog.ImportedItem;
import org.enrico.lastminute.items.catalog.Item;
import org.enrico.lastminute.items.catalog.TaxFreeItem;
import org.enrico.lastminute.shoppingcart.IShoppingCart;
import org.enrico.lastminute.shoppingcart.impl.ShoppingCart;
import org.junit.jupiter.api.Test;

class AppTest {

	@Test
	void itTestCase1() {
		
		/*
		 	Input 1:
			1 book at 12.49
			1 music CD at 14.99
			1 chocolate bar at 0.85
			------------------------------
			Output 1:
			1 book : 12.49
			1 music CD: 16.49
			1 chocolate bar: 0.85
			Sales Taxes: 1.50
			Total: 29.83
		*/
		
		final String EXPECTED_RECEIPT1 = new StringBuilder()
				.append("1 book: 12.49\n")
				.append("1 music CD: 16.49\n")
				.append("1 chocolate bar: 0.85\n")
				.append("Sales Taxes: 1.50\n")
				.append("Total: 29.83").toString();
		
		//GIVEN
		IShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartItem aBook = new TaxFreeItem("book", 1, BigDecimal.valueOf(12.49));
		IShoppingCartItem aMusicCD= new Item("music CD", 1, BigDecimal.valueOf(14.99));
		IShoppingCartItem aChocolateBar = new TaxFreeItem("chocolate bar", 1, BigDecimal.valueOf(0.85));

		//WHEN
		shoppingCart.addItem(aBook).addItem(aMusicCD).addItem(aChocolateBar);
		String actualReceipt = shoppingCart.getReceipt();
		
		//THEN
		assertEquals(EXPECTED_RECEIPT1, actualReceipt);
		
	} 
	
	@Test
	void itTestCase2() {
		
		/*	
			Input 2:
			1 imported box of chocolates at 10.00
			1 imported bottle of perfume at 47.50
			-------------------------------------
			Output 2:
			1 imported box of chocolates: 10.50
			1 imported bottle of perfume: 54.65
			Sales Taxes: 7.65
			Total: 65.15
		 */
		
		final String EXPECTED_RECEIPT2 = new StringBuilder()
				.append("1 imported box of chocolates: 10.50\n")
				.append("1 imported bottle of perfume: 54.65\n")
				.append("Sales Taxes: 7.65\n")
				.append("Total: 65.15").toString();
		
		//GIVEN
		IShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartItem aBoxOfChocolate = new ImportedItem("imported box of chocolates", 1, BigDecimal.valueOf(10.00), AbstractItem.NO_TAX);
		IShoppingCartItem aBottleOfPerfum = new ImportedItem("imported bottle of perfume", 1, BigDecimal.valueOf(47.50));
		

		//WHEN
		shoppingCart.addItem(aBoxOfChocolate).addItem(aBottleOfPerfum);
		String actualReceipt = shoppingCart.getReceipt();
		
		//THEN
		assertEquals(EXPECTED_RECEIPT2, actualReceipt);
	}
	
	@Test
	void itTestCase3() {
		
		/*	
			Input 3:
			1 imported bottle of perfume at 27.99
			1 bottle of perfume at 18.99
			1 packet of headache pills at 9.75
			1 box of imported chocolates at 11.25
			-------------------------------------
			Output 3:
			1 imported bottle of perfume: 32.19
			1 bottle of perfume: 20.89
			1 packet of headache pills: 9.75
			1 imported box of chocolates: 11.85
			Sales Taxes: 6.70
			Total: 74.68
	 */
		
		final String EXPECTED_RECEIPT3 = new StringBuilder()
				.append("1 imported bottle of perfume: 32.19\n")
				.append("1 bottle of perfume: 20.89\n")
				.append("1 packet of headache pills: 9.75\n")
				.append("1 imported box of chocolates: 11.85\n")				
				.append("Sales Taxes: 6.70\n")
				.append("Total: 74.68").toString();
		
		//GIVEN
		IShoppingCart shoppingCart = new ShoppingCart();
		IShoppingCartItem aBottleOfPerfum = new ImportedItem("imported bottle of perfume", 1, BigDecimal.valueOf(27.99));
		IShoppingCartItem aBottleOfPerfume = new Item("bottle of perfume", 1, BigDecimal.valueOf(18.99));
		IShoppingCartItem aPacketOfHeadachePills = new TaxFreeItem("packet of headache pills", 1, BigDecimal.valueOf(9.75));
		IShoppingCartItem aBoxOfChocolate = new ImportedItem("imported box of chocolates", 1, BigDecimal.valueOf(11.25), AbstractItem.NO_TAX);
		

		//WHEN
		shoppingCart.addItem(aBottleOfPerfum).addItem(aBottleOfPerfume).addItem(aPacketOfHeadachePills)
					.addItem(aBoxOfChocolate);
		
		String actualReceipt = shoppingCart.getReceipt();
		
		//THEN
		assertEquals(EXPECTED_RECEIPT3, actualReceipt);
	}
	
}
