package com.google.unizone.server;

public class Available {
	public Available(IStudent student,int beginTm,int endTm,String message){
		this.student=student;
		this.beginTm=beginTm;
		this.endTm=endTm;
		this.message=message;
	}

	IStudent student;
	public IStudent getStudent() {
		return student;
	}
	public void setStudent(IStudent student) {
		this.student = student;
	}
	public int getBeginTm() {
		return beginTm;
	}
	public void setBeginTm(int beginTm) {
		this.beginTm = beginTm;
	}
	public int getEndTm() {
		return endTm;
	}
	public void setEndTm(int endTm) {
		this.endTm = endTm;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	int beginTm, endTm;
	String message;
	
	
	
}
