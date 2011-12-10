package com.google.unizone.server;

import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.EmbeddedOnly;

@EmbeddedOnly
public class MyDate {
	@Persistent
	private int day, month, year;
	@Persistent
	private MyTime hour;
	
	public MyDate(int day, int month, int year, MyTime hour) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
	}
	
	public MyDate(int day, int month, int year) {
		this(day, month, year, new MyTime(9,0));
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
	
	public MyTime getHour() {
		return this.hour;
	}
	
	public String getHourStr() {
		return this.hour.toString();
	}
	public String toString(){
		return Integer.toString(this.day)+"/"+Integer.toString(this.month)+"/"+Integer.toString(this.year)+" "+this.hour.toString();
	}
}
