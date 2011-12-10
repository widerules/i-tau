package com.google.code.iTau;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/*
 * this class expresses a session of a course
 * example : the course descrete math is taught 
 * on sunday 10-12 and on monday 12-14.
 * each one of those is called a lesson.
 */

@PersistenceCapable
public class Lesson implements ILesson {
	
	
	/*public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	    THURSDAY, FRIDAY, SATURDAY 
	}*/
	@Persistent
	private MyTime beginTime,endTime;
	@Persistent
	private int lessDay;
	@Persistent
	private String place;
	
	public Lesson(int day,MyTime beginTime,MyTime endTime, String place){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
		this.place = place;
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
}
