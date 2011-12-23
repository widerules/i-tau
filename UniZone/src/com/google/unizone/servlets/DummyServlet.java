
package com.google.unizone.servlets;

import javax.servlet.http.HttpServlet;


import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.google.unizone.server.Course_DB;
import com.google.unizone.server.DBSimpleLogic;
import com.google.unizone.server.DB_Logic;
import com.google.unizone.server.ICourse;
import com.google.unizone.server.ILesson;
import com.google.unizone.server.IShiur;
import com.google.unizone.server.ITest;
import com.google.unizone.server.IStudent;
import com.google.unizone.server.ITestStudent;
import com.google.unizone.server.Lesson;
import com.google.unizone.server.MyDate;
import com.google.unizone.server.MyTest;
import com.google.unizone.server.MyTime;
import com.google.unizone.server.ShiurComparator;
import com.google.unizone.server.TauCourse;
import com.google.unizone.server.TauStudent;
import com.google.unizone.server.TestStudet;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/*
 * this servlet is for getting 2 different time tables:
 * 1. semester classes ('classes')
 * 2. semester final tests ('tests')
 */
@SuppressWarnings("serial")
public class DummyServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		/*IStudent sinai = new TauStudent("43", "all", "http://www.google.com/imgres?um=1&hl=en&sa=N&biw=1280&bih=598&tbm=isch&tbnid=5wc248UHnWCIgM:&imgrefurl=http://www.ihavenothing.org/2010/02/17/pastor-screens-gay-porn-in-church/&docid=ly_IwESLKxcxcM&imgurl=http://www.ihavenothing.org/wp-content/uploads/2010/02/justin-gaston-gay-porn-scandal.jpg&w=400&h=578&ei=spXjTuekEtCo8APY_pWBBA&zoom=1&iact=rc&dur=70&sig=111894031363263557074&page=6&tbnh=120&tbnw=87&start=117&ndsp=23&ved=1t:429,r:9,s:117&tx=59&ty=58","sinai gross", "cs", 4, "tau_1 tau_2");
		DB_Logic.createStudent(sinai);
		System.out.println("HERE");  
		IStudent sinai2 = DB_Logic.getStudent("43");
		System.out.println("THERE");
		System.out.println(sinai2.getName());
		
		
		*/
		
		/*
		MyTime bTimeLesson1Hedva1 = new MyTime(15, 00);
        MyTime eTimeLesson1Hedva1 = new MyTime(17, 00);
        Lesson lesson1Hedva1 = new Lesson(3, bTimeLesson1Hedva1, eTimeLesson1Hedva1, "Dach Auditarium 005");
        MyTime bTimeLesson2Hedva1 = new MyTime(15, 00);
        MyTime eTimeLesson2Hedva1 = new MyTime(17, 00);
        Lesson lesson2Hedva1 = new Lesson(5, bTimeLesson2Hedva1, eTimeLesson2Hedva1, "Dach Auditarium 005");
        MyDate maHedva1 = new MyDate(14, 02, 2012);
        MyDate mbHedva1 = new MyDate(16, 03, 2012);
        List<ILesson> lessonsHedva = new LinkedList<ILesson>();
        lessonsHedva.add(lesson1Hedva1);
        lessonsHedva.add(lesson2Hedva1);
       
        MyTime bTimeLesson1LinearAlgebra1 = new MyTime(13, 00);
        MyTime eTimeLesson1LinearAlgebra1 = new MyTime(15, 00);
        Lesson lesson1LinearAlgebra1 = new Lesson(3, bTimeLesson1LinearAlgebra1, eTimeLesson1LinearAlgebra1, "Dach Auditarium 005");
        MyTime bTimeLesson2LinearAlgebra1 = new MyTime(13, 00);
        MyTime eTimeLesson2LinearAlgebra1 = new MyTime(15, 00);
        Lesson lesson2LinearAlgebra1 = new Lesson(5, bTimeLesson2LinearAlgebra1, eTimeLesson2LinearAlgebra1, "Dach Auditarium 005");
        MyDate maLinearAlgebra1 = new MyDate(7, 2, 2012);
        MyDate mbLinearAlgebra1 = new MyDate(9, 3, 2012);
        List<ILesson> lessonsLinearAlgebra = new LinkedList<ILesson>();
        lessonsLinearAlgebra.add(lesson1LinearAlgebra1);
        lessonsLinearAlgebra.add(lesson2LinearAlgebra1);
        
        MyTime bTimeLesson1Bdida = new MyTime(12, 00);
        MyTime eTimeLesson1Bdida = new MyTime(14, 00);
        Lesson lesson1Bdida = new Lesson(1, bTimeLesson1Bdida, eTimeLesson1Bdida, "Lev Auditarium 009");
        MyTime bTimeLesson2Bdida = new MyTime(17, 00);
        MyTime eTimeLesson2Bdida = new MyTime(19, 00);
        Lesson lesson2Bdida = new Lesson(2, bTimeLesson2Bdida, eTimeLesson2Bdida, "Lev Auditarium 009");
        MyDate maBdida = new MyDate(26, 2, 2012);
        MyDate mbBdida = new MyDate(21, 9, 2012);
        List<ILesson> lessonsBdida = new LinkedList<ILesson>();
        lessonsBdida.add(lesson1Bdida);
        lessonsBdida.add(lesson2Bdida);
        
        MyTime bTimeLesson1ExtendedIntro = new MyTime(16, 00);
        MyTime eTimeLesson1ExtendedIntro = new MyTime(18, 00);
        Lesson lesson1ExtendedIntro = new Lesson(1, bTimeLesson1ExtendedIntro, eTimeLesson1ExtendedIntro, "Dan David 001");
        MyTime bTimeLesson2ExtendedIntro = new MyTime(12, 00);
        MyTime eTimeLesson2ExtendedIntro = new MyTime(14, 00);
        Lesson lesson2ExtendedIntro = new Lesson(4, bTimeLesson2ExtendedIntro, eTimeLesson2ExtendedIntro, "Dan David 001");
        MyDate maExtendedIntro = new MyDate(2, 3, 2012);
        MyDate mbExtendedIntro = new MyDate(30, 3, 2012);
        List<ILesson> lessonsExtendedIntro = new LinkedList<ILesson>();
        lessonsExtendedIntro.add(lesson1ExtendedIntro);
        lessonsExtendedIntro.add(lesson2ExtendedIntro);
		
		
		
		
		MyTime bTime = new MyTime(10, 10); 
		MyTime eTime = new MyTime(10, 30);
		Lesson lesson = new Lesson(1, bTime, eTime, "here"); 
		MyDate ma = new MyDate(1, 1, 2000);
		MyDate mb = new MyDate(1, 1, 2000);
		List<ILesson> lessons = new LinkedList<ILesson>();
		lessons.add(lesson); 
		DB_Logic.createCourse(new TauCourse("0366-1101-01", "Calculus 1A", "Yaron Ostrover", lessonsHedva, maHedva1, mbHedva1, "2012/A", new TreeSet<String>()));
		//ICourse bdida = new TauCourse("tau_2", "bdida", "tarsi", null, null, null, "A", new TreeSet<String>());
		//ICourse infi = new TauCourse("tau_3", "infi", "inna", null, null, null, "A", new TreeSet<String>());
		System.out.println("SINI");
		DB_Logic.createStudent(new TauStudent("822294292", "all","http://profile.ak.fbcdn.net/hprofile-ak-snc4/186097_822294292_2545466_q.jpg", "822294289 822294291 822294290", "Tal Gerbi", "cs", 1, "0366-1101-01"));
		System.out.println("@");
		
		//System.out.println(sinai.getName()); 
		//System.out.println(paulina.getName());
		//System.out.println(ziv.getName());
		//System.out.println(sinai.getFacebookID());
		//Course_DB.createCourse(modelim); 
		 
		System.out.println("HERE");
		IStudent sinai2 =DB_Logic.getStudent("822294292");
		System.out.println(sinai2.getName());
		System.out.println(sinai2.getFacebookID());
		System.out.println(sinai2.getFaculty());
		
		DBSimpleLogic.createStudent(new TestStudet("822294292", "pasi sini"));
		System.out.println("@");
		*/
		 
		System.out.println("DEBUG HERE 1");
		//TauStudent sinai = new TauStudent("1", "all", "url", "2", "sinai", "cs", 3, "0368-1111");
		ITestStudent sinai = new TestStudet("822294292", "pasi sini");
		System.out.println("DEBUG HERE 2");
		//DB_Logic.createStudent(sinai);
		DBSimpleLogic.createStudent(sinai);
		System.out.println("DEBUG HERE 3");
		//TauStudent sinai2 = (TauStudent)DB_Logic.getStudent("1");
		ITestStudent sinai2 = DBSimpleLogic.getStudent("822294292");
		System.out.println("DEBUG HERE 4");
		System.out.println(sinai2.getName());
		
		
		
		
		
		
		
	}
}
