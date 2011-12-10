package com.google.unizone.server;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.unizone.server.TauCourse;
import com.google.unizone.server.TauStudent;


public class Course_DB {
	
	private static HashMap<String, ICourse> course_hash = new HashMap<String, ICourse>();
	private static HashMap<String, IStudent> student_hash = new HashMap<String, IStudent>();
	
	
	public void createCourse(String courseID, String courseName, String professorName, List<ILesson> lessonTimes, MyDate moedADate, MyDate moedBDate, String semester, Set<String> studentIDList){
		TauCourse course=new TauCourse(courseID, courseName, professorName, lessonTimes, moedADate, moedBDate, semester, studentIDList);
		course_hash.put(courseID, course);
	}
	public void createStudent(String facebookID, String accessToken, String name, String faculty, int year, String courseIDList) {
		TauStudent stu=new TauStudent(facebookID,accessToken,name,faculty,year,courseIDList);
		student_hash.put(facebookID, stu);
		Set<String> cur=stu.getCourseIDList();
		for (String s:cur){
			ICourse c=course_hash.get(s);
			c.addStudent(facebookID);
			course_hash.put(s, c);
		}
		
	}
	
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
	@SuppressWarnings("unchecked")
	public static List<IStudent> getCourseStudents(ICourse course) {
		Set<String> studetIDs =course.getStudents();
		List<IStudent> result = new LinkedList<IStudent>();
		for (String studentID : studetIDs) {
			IStudent student = getStudent(studentID);
			result.add(student);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<ICourse> getStudentCourses(IStudent student){
		Set<String> courseIDs = student.getCourseIDList();
		List<ICourse> result = new LinkedList<ICourse>();
		for (String courseID : courseIDs) {
			ICourse course = getCourse(courseID);
			result.add(course);
		}
		return result;
	}
	
	public static void removeStudent(IStudent student) {
		if (student != null) {
			student_hash.remove(student.getFacebookID());
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty student");
		}
	}
	
	public static void removeCourse(ICourse course) {
		if (course != null) {
			course_hash.remove(course.getID());
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty course");
		}
	}
	
	 @SuppressWarnings("unchecked")
	public static List<IShiur> getSchedule(IStudent stu){
			List<ICourse> courses=getStudentCourses(stu);
			List<IShiur> shiurim=new LinkedList<IShiur>();
		    	for (ICourse course : courses){
		    		//@SuppressWarnings("unchecked")
					List<ILesson> lessons=course.getLessonTimes();
		    		for (ILesson lesson : lessons){
		    			shiurim.add(new Shiur(lesson.getDay(), lesson.getBeginTime(), lesson.getEndTime(), lesson.getPlace(), course.getID(), course.getCourseName(), course.getProfessor()));
		    		}
		    	}
		    	Collections.sort(shiurim, new ShiurComparator());
		    	return shiurim;
	}
	 
	 public static Boolean checkIfCourseExist(String id) {
	        return course_hash.containsKey(id);
	    }
	 
	    public static Boolean checkIfStudentExist(String id) {
	    	return student_hash.containsKey(id);
	    }
	    
	    /*public static void addCourseToStudet(IStudent stu, ICourse course) {
	    	stu.addCourseID(course.getID());
	    	createStudent(stu);
	    }
	    
	    public static void addStudentToCourse(IStudent stu, ICourse course) {
	    	course.addStudent(stu.getFacebookID());
	    	createCourse(course);
	    }
	    
	       
	    public static void RemoveCourseFromStudet(IStudent stu, ICourse course) {
	    	stu.removeCourseID(course.getID());
	    	createStudent(stu);
	    }
	    
	    public static void removeStudentFromCourse(IStudent stu, ICourse course) {
	    	course.removeStudent(stu.getFacebookID());
	    	createCourse(course);
	    }
	 */
}
