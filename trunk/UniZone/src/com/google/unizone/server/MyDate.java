package com.google.unizone.server;

import java.io.Serializable;

import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(detachable = "true")
public class MyDate{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id; 	
	
	@Persistent(defaultFetchGroup="true")
	@Embedded
	private MyTime hour;
	
	@Persistent
	private int day, month, year;
	
		

public MyDate(String str)
	{
		int index1, index2;
		index1 = str.indexOf('.');
		index2 = str.lastIndexOf('.');
		this.day = Integer.parseInt(str.substring(0, index1));
		this.month = Integer.parseInt(str.substring(index1+1, index2));
		this.year = Integer.parseInt(str.substring(index2+1, str.length()));
		this.hour = new MyTime(9, 0);
	}
	
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
