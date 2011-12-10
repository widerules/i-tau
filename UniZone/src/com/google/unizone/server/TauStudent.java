package com.google.unizone.server;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class TauStudent implements IStudent {
	
	@PrimaryKey
	@Persistent
	private String facebookID;
	@Persistent
	private String accessToken;
	@Persistent
	private int isFree = 0;
	@Persistent
	private String name;
	@Persistent
	private String faculty;
	@Persistent
	private int year; 
	@Persistent
	private Set<String> courseIDList;
	
	public TauStudent(String facebookID, String accessToken, String name, String faculty, int year, String courseIDList) {
		this.faculty = faculty;
		this.name = name;
		this.year = year;
		this.courseIDList = new TreeSet<String>(Arrays.asList(courseIDList.split(" ")));
		this.accessToken = accessToken;
		this.facebookID = facebookID;
		//str_to_time_table(timeTableStr, timeTable);
	}
	
	//private void str_to_time_table(String timeTableStr, HashMap<String, String> timeTable) {
		//TODO: decide a format in which will come and parse it
	//}
	
	public int isFree() {
		return this.isFree;
	}
	
	//public int isFriendFree(IStudent friend) {
		//return friend.isFree();
	//}
	
	/* 0 - free
	 * 1 - free but 'taken'
	 * 2 - not free
	 * 3 - not in university
	 */
	public void setFree(int free_type) throws Exception {
		if (free_type > 3 || free_type < 0) {
			Exception up =  new Exception("ERROR : Value of free_type must be an integet 0-3\nC");
			throw up;
		}
		this.isFree = free_type;
	}
	public String getFacebookID() {
		return this.facebookID;
	}
	public String getAccessToken() {
		return this.accessToken;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFaculty() {
		return this.faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Set<String> getCourseIDList() {
		return this.courseIDList;
	}
	public void addCourseID(String courseID) {
		if (!this.isTakeCourse(courseID)) {
			this.courseIDList.add(courseID);
		}
	}
	public void removeCourseID(String courseID) {
		if (this.isTakeCourse(courseID)) {
			this.courseIDList.remove(courseID);
		}
	}
	public boolean isTakeCourse(String courseID) {
		return this.courseIDList.contains(courseID);
	}
	
}
