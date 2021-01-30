package com.stock.stockPrice.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.stock.stockPrice.service.FirebaseInitializer;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class App {
	
	public static void main(String[] args) throws IOException {
		
		String[] symbols = getSymbols();
		double price, changePercent, change, volume;
		String name, currency;
		
		Map<String, StockInfo> users = new HashMap<>();
		
		//Fetches the information about all the stocks that are specified in the 
		//Symbols array using Yahoo API 
		Map<String, Stock> stocks = fetch(symbols);
		
		//Goes through every stock and print the information about each stock
		for(String st: symbols) {
			price = getPrice(stocks, st);
			changePercent = stocks.get(st).getQuote().getChangeInPercent().doubleValue();
			change = stocks.get(st).getQuote().getChange().doubleValue();
			volume = getVolume(stocks, st);
			name = getName(stocks, st);
			currency = getCurrency(stocks, st);
			
			//Saves the stock's information in a map
			users.put(st, new StockInfo(name, price, currency, change, changePercent, volume));
			
			System.out.println("Symbol: " + st + "\nName: " + name);
			System.out.printf("Price: $%.2f\n", price);
			System.out.println("Currency: " + currency + "\nChange: " + change + 
							   "\nChange Percent: " + changePercent + "%"); 
			volume /= 1000000; 
			System.out.printf("Volume: %.3fM\n\n", volume);
		}
				
		//Send the map containing the information about stocks to be stored on FireBase database
		FirebaseInitializer.update(users);
	}
	
	public static Map<String, Stock> fetch(String[] symbols) throws IOException {
		
		Map<String, Stock> stocks = YahooFinance.get(symbols); 		
		return stocks;
	}

	public static String getCurrency(Map<String, Stock> stocks, String st) {
		return stocks.get(st).getCurrency();
	}
	
	public static String getName(Map<String, Stock> stocks, String st) {
		return stocks.get(st).getName();
	}
	
	public static String[] getSymbols() {
		return new String[] {"INTC", "VZ", "TSLA", "FB", "AAPL"};
	}
	
	public static double getVolume(Map<String, Stock> stocks, String st) {
		return stocks.get(st).getQuote().getVolume().doubleValue();
	}
	
	public static double getPrice(Map<String, Stock> stocks, String st) {
		return stocks.get(st).getQuote().getPrice().doubleValue();
	}
}
