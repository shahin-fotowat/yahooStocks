package com.stock.stockPrice.main;

public class StockInfo {

	private double price;
	private double change;
	private double changePercent;
	private String name; 
	private String currency;
	private double volume;
	
	public StockInfo(String name, double price, String currency, double change, double changePercent, double volume) {
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.change = change;
		this.changePercent = changePercent;
		this.volume = volume;
	}
	
	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getChangePercent() {
		return changePercent;
	}

	public void setChangePercent(double changePercent) {
		this.changePercent = changePercent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}