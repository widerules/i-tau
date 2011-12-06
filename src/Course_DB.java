package com.google.code.iTau;


import java.util.HashMap;


public class Course_DB {
	
	private HashMap<String, HashMap<String, ICourse>> course_hash;
	
	public Course_DB(){
		this.course_hash = new HashMap<String, HashMap<String, ICourse>>();
	}
	
	public void update(ICourse course){
		if (!this.isCourseExist(course)){
			String courseID = course.getID();
			String semester = course.getSemester();
			//this.course_hash.put(courseID, new map)
		}
	}
	


	public boolean isCourseExist(ICourse course){
		String courseID = course.getID();
		String semester = course.getSemester();
		if (this.course_hash.containsKey(courseID)){
			if (this.course_hash.get(courseID).get(semester) != null){
				return true;
			}
		}
		return false;
	}
}
