package com.google.unizone.server;
import java.util.LinkedList;
//import org.datanucleus.plugin.*;
import java.util.List; 
import java.util.TreeSet;

import org.junit.Test;

public class sinai_tests {
	@Test
	public void sini() {
		MyTime bTime = new MyTime(10, 10); 
		MyTime eTime = new MyTime(10, 30);
		Lesson lesson = new Lesson(1, bTime, eTime, "here"); 
		MyDate ma = new MyDate(1, 1, 2000);
		MyDate mb = new MyDate(1, 1, 2000);
		List<ILesson> lessons = new LinkedList<ILesson>();
		lessons.add(lesson);
		ICourse modelim = new TauCourse("tau_1", "modelim", "anna", lessons, ma, mb, "A", new TreeSet<String>());
		//ICourse bdida = new TauCourse("tau_2", "bdida", "tarsi", null, null, null, "A", new TreeSet<String>());
		//ICourse infi = new TauCourse("tau_3", "infi", "inna", null, null, null, "A", new TreeSet<String>());
		
		IStudent sinai = new TauStudent("1", "all", "sinai gross", "cs", 4, "tau_1 tau_2");
		IStudent paulina = new TauStudent("2", "all", "paulina tsirlin", "cs", 4, "tau_1 tau_3");
		IStudent ziv = new TauStudent("3", "all", "ziv hasday", "cs", 3, "tau_3 tau_2");
		
		System.out.println(sinai.getName()); 
		System.out.println(paulina.getName());
		System.out.println(ziv.getName());
		System.out.println(sinai.getFacebookID());
		DB_Logic.createCourse(modelim);
		System.out.println("HERE");  
		IStudent sinai2 = DB_Logic.getStudent("1");
		System.out.println(sinai2.getName());
	}
}
