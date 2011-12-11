package com.google.unizone.server;

import java.util.LinkedList;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.annotations.PersistenceCapable;  
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class TauCourse implements ICourse{
	
	@PrimaryKey
	@Persistent
	/*
	 * courseID will be in the format of:
	 * uniID_yyyy/(A|B)
	 */
	private String courseID;
	@Persistent
	private String courseName;
	@Persistent
	private String professorName;
	@Persistent
	private List<ILesson> lessonTimes;
	@Persistent
	private MyDate moedADate;
	@Persistent
	private MyDate moedBDate;
	@Persistent
	private String semester;
	@Persistent
	private Set<String> studentIDList;
	
	//TODO add lesson Times parsing
	public TauCourse(String courseID, String courseName, String professorName, List<ILesson> lessonTimes, MyDate moedADate, MyDate moedBDate, String semester, Set<String> studentIDList){
		this.courseID = courseID;
		this.courseName = courseName;
		this.professorName = professorName;
		this.lessonTimes = new LinkedList<ILesson>(lessonTimes);
		this.moedADate = new MyDate(moedBDate);
		this.moedBDate = new MyDate(moedBDate);
		this.semester = semester;
		this.studentIDList = new TreeSet<String>(studentIDList);
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	public String getProfessor() {
		return this.professorName;
	}
	public List<ILesson> getLessonTimes() {
		return this.lessonTimes;
	}
	public MyDate getMoedADate() {
		return this.moedADate;
	}
	public MyDate getMoedBDate() {
		return this.moedBDate;
	}
	public String getID() {
		return this.courseID;
	}
	public String getSemester() {
		return this.semester;
	}
	public Set<String> getStudents() {
		return this.studentIDList;
	}
	
	//SETTERS
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setProfessor(String name) {
		this.professorName = name;
	}
	public void setLessonTimes(List<ILesson> lessonTimes) {
		this.lessonTimes = lessonTimes;
	}
	//public void setMoedADate(FORMAT??);
	//public void setMoedBDate(FORMAT??);
	public void setID(String courseID) {
		this.courseID  = courseID; 
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public void addStudent(String id) {
		if (!isStudentInCourse(id)) {
			this.studentIDList.add(id);
		}
	}
	public void removeStudent(String id) {
		if (isStudentInCourse(id)) {
			this.studentIDList.remove(id);
		}
	}
	public void addStudent(IStudent stu) {
		if (!isStudentInCourse(stu)) {
			this.studentIDList.add(stu.getFacebookID());
		}
	}
	public void removeStudent(IStudent stu) {
		if (!isStudentInCourse(stu)) {
			this.studentIDList.remove(stu.getFacebookID());
		}
	}
	public boolean isStudentInCourse(String studentID) {
		return this.studentIDList.contains(studentID);
	}
	public boolean isStudentInCourse(IStudent student) {
		return isStudentInCourse(student.getFacebookID());
	}
	
	public String toString(boolean isHeader)
	{
		String temp;
		if (isHeader) {
			return "<h2 id=\"course_toString\">" + this.getCourseName() +  "-"  + this.getProfessor()
					+  "(" + this.getID() + ") </h2>";
		}
		else {
			return "<A href = \"CourseGroupPage.jsp?courseID=" + this.getID() + "\" id=\"course_toString\">" + this.getCourseName() +  "-"  + this.getProfessor()
					+  "(" + this.getID() + ") </A>";
		}
			
				}

}
