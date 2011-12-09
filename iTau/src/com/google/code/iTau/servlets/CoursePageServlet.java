package com.google.code.iTau.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.google.code.iTau.DB_Logic;
import com.google.code.iTau.ICourse;
import com.google.code.iTau.IStudent;

/*
 * this servlet if for getting all students studying in a course
 * will return all relevant facebookID's and their access token
 */
@SuppressWarnings("serial")
public class CoursePageServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String courseID = request.getParameter("courseID");
		ICourse course = DB_Logic.getCourse(courseID);
		List<IStudent> courseStudents = DB_Logic.getCourseStudents(course);
		Map<String, String>	IDsAndAccess = new HashMap<String, String>();
		for (IStudent student : courseStudents) {
			IDsAndAccess.put(student.getFacebookID(), student.getAccessToken());
		}
		try {
			ServletUtils.writeToResponseAsJson(response, IDsAndAccess);
		} catch(IOException ioe) {
			System.out.println("ERROR : (CoursePageServlet) Failed to write response");
		}
	}
}
