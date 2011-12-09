package com.google.code.iTau;
import java.util.LinkedList;
import java.util.TreeSet;

import com.google.code.iTau.servlets.CoursePageServlet;

public class sinai_tests {
	public static void main(String[] args) {
		//ICourse modelim = new TauCourse("tau_1", "modelim", "anna", null, null, null, "A", new TreeSet<String>());
		//ICourse bdida = new TauCourse("tau_2", "bdida", "tarsi", null, null, null, "A", new TreeSet<String>());
		//ICourse infi = new TauCourse("tau_3", "infi", "inna", null, null, null, "A", new TreeSet<String>());
		
		IStudent sinai = new TauStudent("1", "all", "sinai gross", "cs", 4, "tau_1 tau_2");
		IStudent paulina = new TauStudent("2", "all", "paulina tsirlin", "cs", 4, "tau_1 tau_3");
		IStudent ziv = new TauStudent("3", "all", "ziv hasday", "cs", 3, "tau_3 tau_2");
		
		System.out.println(sinai.getName());
		System.out.println(paulina.getName());
		System.out.println(ziv.getName());
		System.out.println(sinai.getFacebookID());
		DB_Logic.createStudent(sinai);
	}
}
