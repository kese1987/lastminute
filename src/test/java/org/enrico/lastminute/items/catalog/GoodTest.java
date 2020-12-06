package org.enrico.lastminute.items.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GoodTest {

	@ParameterizedTest
	@MethodSource("netPriceSource")
	void testInvalidPrice(BigDecimal netPrice) {
		assertThrows(IllegalArgumentException.class, () -> new MockGood("item", 1, netPrice));
	}

	public static Stream<Arguments> netPriceSource() {
		return Stream.of(Arguments.of(BigDecimal.valueOf(-1d)), Arguments.of(BigDecimal.ZERO));
	}

	@ParameterizedTest
	@MethodSource("qtySource")
	void testInvalidQty(int qty) {
		assertThrows(IllegalArgumentException.class, () -> new MockGood("", qty));
	}

	public static Stream<Arguments> qtySource() {
		return Stream.of(Arguments.of(-1), Arguments.of(0));
	}

	@Test
	void testToStringWithDescription() {
		MockGood good = new MockGood("description", 2, BigDecimal.ONE);
		assertEquals("2 description: 2.20", good.toString());
	}

	@Test
	void testToString() {
		MockGood good = new MockGood();
		assertEquals("1 Article: 1.10", good.toString());
	}

	@Test
	void testRounding() {

		MockGood good = new MockGood();

		assertNull(good.taxRound(null));
		assertEquals("1.00", good.taxRound(BigDecimal.valueOf(1)).toString());
		assertEquals("1.05", good.taxRound(BigDecimal.valueOf(1.03)).toString());
		assertEquals("1.10", good.taxRound(BigDecimal.valueOf(1.1)).toString());
		assertEquals("1.15", good.taxRound(BigDecimal.valueOf(1.15)).toString());
		assertEquals("1.20", good.taxRound(BigDecimal.valueOf(1.17)).toString());
		assertEquals("1.15", good.taxRound(BigDecimal.valueOf(1.13)).toString());
		assertEquals("1.10", good.taxRound(BigDecimal.valueOf(1.07)).toString());
		assertEquals("1.10", good.taxRound(BigDecimal.valueOf(1.07999999)).toString());

	}

	private static class MockGood extends AbstractItem {

		public MockGood(String description, int qty, BigDecimal netPrice) {
			super(description, qty, netPrice);
		}

		public MockGood(String description, int qty) {
			super(description, qty, BigDecimal.ONE);
		}

		public MockGood() {
			super("", 1, BigDecimal.ONE);
		}
	}
}
