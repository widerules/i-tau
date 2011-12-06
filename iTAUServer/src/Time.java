package com.google.code.iTau;

public class Time {
	private int hours,minutes;
	
	public int getHours(){
		return hours;
	}
	
	public int getMinutes(){
		return minutes;
	}
	
	public String toString(){
		return Integer.toString(hours)+":"+Integer.toString(minutes);
	}
}
