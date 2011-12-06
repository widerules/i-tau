package com.google.code.iTau;

/*
 * this class expresses a session of a course
 * example : the course descrete math is taught 
 * on sunday 10-12 and on monday 12-14.
 * each one of those is called a lesson.
 */
public class Lesson implements ILesson {
	
	
	public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	    THURSDAY, FRIDAY, SATURDAY 
	}
	private Time beginTime,endTime;
	private Day lessDay;
	
	public Lesson(Day day,Time beginTime,Time endTime){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
	}
	
	public Time getBeginTime(){
		return beginTime;
	}
	public Time getEndTime()
	{
		return endTime;
	}
	public Day getDay(){
		return lessDay;
	}
	public String getPlace(){
		return "";
	}
}
