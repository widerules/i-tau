package com.google.code.iTau;

import java.util.Set;


public interface IStudent {
	
	/* int because may be free but 'taken' by other person/activity */
	public int isFree();
	//public int isFriendFree(IStudent friend);
	public void setFree(int free_type) throws Exception;
	public String getName();
	public void setName(String name);
	public String getFaculty();
	public void setFaculty(String faculty);
	public void setYear(int year);
	public int getYear();
	public String getFacebookID();
	public String getAccessToken();
	public Set<String> getCourseIDList();
	public void addCourseID(String courseID);
	public void removeCourseID(String courseID);
	public boolean isTakeCourse(String courseID);
	
}
