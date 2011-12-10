package com.google.code.iTau;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.Persistent;

@EmbeddedOnly
public class MyTime {
	@Persistent
	private int hours,minutes;
	//private String hoursStr,minutesStr;
	
	public MyTime(int hours,int minutes){
		this.hours=hours;
		this.minutes=minutes;
	}
	
	public int getHours(){
		return hours;
	}
	
	public int getMinutes(){
		return minutes;
	}
	
	public String getStringHours(){
		if (this.hours>=0 && this.hours<=9){
			return "0"+Integer.toString(this.hours);
		}
		return Integer.toString(hours);
	}
	
	public String getStringMinutes(){
		if (this.minutes>=0 && this.minutes<=9){
			return "0"+Integer.toString(this.minutes);
		}
		return Integer.toString(this.minutes);
	}

	public String toString(){
		return getStringHours()+":"+getStringMinutes();
	}
}
