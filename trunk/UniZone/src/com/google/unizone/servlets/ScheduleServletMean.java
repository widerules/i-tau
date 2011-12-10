package com.google.unizone.servlets;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.google.unizone.server.Course_DB;
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
public class ScheduleServletMean extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String facebookID = request.getParameter("facebookID");
		String semester = request.getParameter("semester");
		String scheduleType = request.getParameter("schedule");
		
		IStudent student = Course_DB.getStudent(facebookID);
		List<ICourse> courseList = Course_DB.getStudentCourses(student);
		if ( scheduleType.equals("classes")) {
			List<IShiur> lessons = Course_DB.getSchedule(student);
			//for (ICourse course : courseList) {
				//if (course.getSemester().equals(semester)) {
					//lessons.addAll(course.getLessonTimes());
				//}
			//}
			Collections.sort(lessons, new ShiurComparator());
			try {
				ServletUtils.writeToResponseAsJson(response, lessons);
			} catch(IOException ioe) {
				System.out.println("ERROR : (ScheduleServlet) Failed to write response");
			}
		} else {
			if (scheduleType.equals("tests")) {
				List<ITest> tests = new LinkedList<ITest>();
				for (ICourse course : courseList) {
					if (course.getSemester().equals(semester)) {
						ITest test_a = new MyTest(course.getCourseName(), course.getMoedADate(), course.getProfessor(), "UNKNOWN", "A", -1);
						ITest test_b = new MyTest(course.getCourseName(), course.getMoedBDate(), course.getProfessor(), "UNKNOWN", "B", -1);
						tests.add(test_a);
						tests.add(test_b);
					}
				}
				Collections.sort(tests, new TestComparator());
				try {
					ServletUtils.writeToResponseAsJson(response, tests);
				} catch(IOException ioe) {
					System.out.println("ERROR : (ScheduleServlet) Failed to write response");
				}
			}
		}
	}
}
