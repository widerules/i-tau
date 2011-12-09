package com.google.code.iTau;


public class MyTest implements ITest{
	
	private String courseName;
	private MyDate date;
	private String proffesor;
	private String location;
	private String moed;
	private int grade;
	
	/*
	 * if grade is not known will be called with -1 and that will be a sign not to display a grade for the client
	 */
	public MyTest(String courseName, MyDate date, String proffesor, String location, String moed, int grade) {
		this.courseName = courseName;
		this.date = date;
		this.proffesor = proffesor;
		this.location = location;
		this.moed = moed;
		this.grade = grade;
	}
	public MyDate getDate() {
		return this.date;
	}
	public String getProffesor() {
		return this.proffesor;
	}
	public String getLocation() {
		return this.location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMoed() {
		return this.moed;
	}
	public String getCourseName() {
		return this.courseName;
	}
	public int getGrade() {
		return this.grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

}
