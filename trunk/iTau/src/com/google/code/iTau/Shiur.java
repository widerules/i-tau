package com.google.code.iTau;

public class Shiur implements IShiur{
	
	private MyTime beginTime,endTime;
	private int lessDay;
	private String place;
	private String courseID;
	private String courseName;
	private String professorName;
	
	public Shiur(int day,MyTime beginTime,MyTime endTime, String place, String courseID, String courseName, String professorName){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
		this.place = place;
		this.courseID = courseID;
		this.courseName = courseName;
		this.professorName = professorName;
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
	public String getPlace(){
		return this.place;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	public String getProfessor() {
		return this.professorName;
	}
	public String getID() {
		return this.courseID;
	}
}
