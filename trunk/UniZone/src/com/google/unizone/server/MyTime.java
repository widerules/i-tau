package com.google.unizone.server;

import java.io.Serializable;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable(detachable = "true")
@EmbeddedOnly
public class MyTime{

	//@PrimaryKey
	//@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	//@Extension(vendorName="datanucleus", key="gae.encoded-pk", value= "true")
	//private String myKey;
	@Persistent
	private int hours;
	 
	@Persistent
	private int minutes;
	//private String hoursStr,minutesStr;
	public MyTime(String hour)
	{
		this.hours = Integer.parseInt(hour) / 100;
		this.minutes = Integer.parseInt(hour) % 100;
	}
	
	
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
