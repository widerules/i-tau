


package com.google.code.iTau.servlets;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.google.code.iTau.DB_Logic;
import com.google.code.iTau.ICourse;
import com.google.code.iTau.IShiur;
import com.google.code.iTau.ITest;
import com.google.code.iTau.IStudent;
import com.google.code.iTau.MyTest;
import com.google.code.iTau.ShiurComparator;

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
public class HomePageScheduale extends HttpServlet{

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
