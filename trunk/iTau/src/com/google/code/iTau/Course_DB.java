package com.google.code.iTau;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Course_DB {
	
	private static HashMap<String, ICourse> course_hash = new HashMap<String, ICourse>();
	private static HashMap<String, IStudent> student_hash = new HashMap<String, IStudent>();
	
	
	public static void createCourse(ICourse course) {
		course_hash.put(course.getID(), course);
	}
	public static void createStudent(IStudent student) {
		student_hash.put(student.getFacebookID(), student);
	}
	
	public static ICourse getCourse(String courseID) {
		return course_hash.get(courseID);
	}
	public static IStudent getStudent(String facebookID) {
		return student_hash.get(facebookID);
	}
	public static List<IStudent> getCourseStudents(ICourse course) {
		Set<String> studetIDs = course.getStudents();
		List<IStudent> result = new LinkedList<IStudent>();
		for (String studentID : studetIDs) {
			IStudent student = getStudent(studentID);
			result.add(student);
		}
		return result;
	}
	public static List<ICourse> getStudentCourses(IStudent student){
		Set<String> courseIDs = student.getCourseIDList();
		List<ICourse> result = new LinkedList<ICourse>();
		for (String courseID : courseIDs) {
			ICourse course = getCourse(courseID);
			result.add(course);
		}
		return result;
	}

}
