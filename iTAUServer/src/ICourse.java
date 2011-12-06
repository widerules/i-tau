package com.google.code.iTau;

import java.util.Set;
import java.util.List;


//useless comment returns! 
// another one
// waddup?
public interface ICourse {


	//GETTERS
	public String getCourseName();
	public String getProfessor();
	public List<ILesson> getLessonTimes();
	public MyDate getMoedADate();
	public MyDate getMoedBDate();
	public String getID();
	public String getSemester();
	public Set<String> getStudents();
	
	//SETTERS
	public void setCourseName(String courseName);
	public void setProfessor(String name);
	public void setLessonTimes(List<ILesson> lessonTimes);
	//public void setMoedADate(FORMAT??);
	//public void setMoedBDate(FORMAT??);
	public void setID(String courseID);
	public void setSemester(String sem);
	public void addStudent(String id);
	public void removeStudent(String id);
	public void addStudent(IStudent stu);
	public void removeStudent(IStudent stu);
}