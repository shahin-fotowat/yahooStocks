package com.stock.stockPrice.main;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class AppTest {
	
	@Test
	void shouldReturnStockName() throws IOException {
		assertAll(
			() -> assertTrue("Apple Inc.".equals(App.getName(App.fetch(App.getSymbols()), "AAPL"))),
			() -> assertTrue("Tesla, Inc.".equals(App.getName(App.fetch(App.getSymbols()), "TSLA")))
		);
	}
	
	@Test
	void shouldReturnStockCurrency() throws IOException {
		assertAll(
			() -> assertTrue("USD".equals(App.getCurrency(App.fetch(App.getSymbols()), "FB"))),
			() -> assertTrue("USD".equals(App.getCurrency(App.fetch(App.getSymbols()), "VZ")))
		);
	}
	
	@Test
	void shouldReturnPositiveVolume() throws IOException {
		assertAll(
			() -> assertTrue(App.getVolume(App.fetch(App.getSymbols()), "FB") > 0),
			() -> assertTrue(App.getVolume(App.fetch(App.getSymbols() ), "TSLA") > 0)
		);
	}
	
	@Test
	void shouldReturnPositivePrice() throws IOException {
		assertAll(
			() -> assertTrue(App.getPrice(App.fetch(App.getSymbols()), "VZ") > 0),
			() -> assertTrue(App.getPrice(App.fetch(App.getSymbols() ), "INTC") > 0)
		);
	}
	
	@Test
	void shouldReturnStocks() throws IOException {
		assertTrue(App.fetch(App.getSymbols()) != null);
	}
	
	@Test
	void shouldReturnStocksSize() throws IOException {
		assertTrue(App.fetch(App.getSymbols()).size() == 5);
	}
}
