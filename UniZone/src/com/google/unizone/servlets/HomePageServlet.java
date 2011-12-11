


package com.google.unizone.servlets;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.google.unizone.server.DB_Logic;
import com.google.unizone.server.ICourse;
import com.google.unizone.server.IShiur;
import com.google.unizone.server.ITest;
import com.google.unizone.server.IStudent;
import com.google.unizone.server.MyTest;
import com.google.unizone.server.ShiurComparator;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * this servlet is for getting 2 different time tables:
 * 1. semester classes ('classes')
 * 2. semester final tests ('tests')
 */
@SuppressWarnings("serial")
public class HomePageServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String facebookID = request.getParameter("facebookID");
		IStudent student = DB_Logic.getStudent(facebookID);
		List<ICourse> courseList = DB_Logic.getStudentCourses(student);
		response.setContentType("application/json");
		try {
			ServletUtils.writeToResponseAsJson(response, courseList);
		} catch(IOException ioe) {
			System.out.println("ERROR : (CoursePageServlet) Failed to write response");
		}
		

	}
}
