package com.google.code.iTau;

public class MyDate {
	private int day, month, year;
	private String hour;
	
	public MyDate(int day, int month, int year, String hour) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
	}
	
	public MyDate(int day, int month, int year) {
		this(day, month, year, "00:00");
	}
	
	public MyDate(MyDate date) {
		this.day = date.getDay();
		this.month = date.getMonth();
		this.year = date.getYear();
		this.hour = date.getHour();
	}
	
	public int getDay(){
		return this.day;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public String getHour() {
		return this.hour;
	}
	
	public String toString(){
		return Integer.toString(this.day)+"/"+Integer.toString(this.month)+"/"+Integer.toString(this.year)+" "+this.hour;
	}
}
