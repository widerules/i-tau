package com.google.code.iTau;
import javax.jdo.annotations.PersistenceCapable;;

@PersistenceCapable
public interface ILesson {
	public MyTime getBeginTime();
	public MyTime getEndTime();
	public int getDay();
	public String getPlace();
}
