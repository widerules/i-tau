package com.google.unizone.server;

public class Shiur implements IShiur{
	
	private MyTime beginTime,endTime;
	private int lessDay;
	private String courseID;
	private String courseName;

	
	public Shiur(int day,MyTime beginTime,MyTime endTime, String courseID, String courseName){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
		this.courseID = courseID;
		this.courseName = courseName;
	}
	
	public MyTime getBeginTime(){
		return beginTime;
	}
	public MyTime getEndTime(){
		return endTime;
	}
	public int getDay(){
		return lessDay;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getID() {
		return this.courseID;
	}
	
	public String nameToString (){
		return this.getCourseName() + " (" + this.getID() + ")";  
	}
}
