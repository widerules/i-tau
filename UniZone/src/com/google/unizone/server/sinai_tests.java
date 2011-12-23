package com.google.unizone.server;
import java.util.LinkedList;

import org.datanucleus.plugin.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;

import org.junit.Test;

public class sinai_tests {
	@Test
	public void sini() { 
		
		//ICourse modelim = new TauCourse("tau_1", "modelim", "anna", lessons, ma, mb, "A", new TreeSet<String>());
		//ICourse bdida = new TauCourse("tau_2", "bdida", "tarsi", null, null, null, "A", new TreeSet<String>());
		//ICourse infi = new TauCourse("tau_3", "infi", "inna", null, null, null, "A", new TreeSet<String>());
		
		/*Course_DB.createStudent("1", "all","PIC", "2 3", "sinai gross", "cs", 4, "tau_1 tau_2");
		Course_DB.createStudent("2", "all","PIC", "3 1", "paulina tsirlin", "cs", 4, "tau_1 tau_3");
		Course_DB.createStudent("3", "all","PIC", "1 2", "ziv hasday", "cs", 3, "tau_3 tau_2");
		
		
		 
		System.out.println("HERE");  
		IStudent sinai2 = Course_DB.getStudent("1");
		System.out.println(sinai2.getName());
		
		Map<Integer, Map<Integer, IStudent>> result = Course_DB.findFreeFriends(sinai2);
		System.out.println(result.size());*/
		
		
		///////////////////////////////////////////////
		/*MyTime bTime = new MyTime(19, 10); 
		MyTime eTime = new MyTime(20, 10);
		Lesson lesson = new Lesson(1, bTime, eTime, "here"); 
		MyDate ma = new MyDate(1, 1, 2000);
		MyDate mb = new MyDate(1, 1, 2000);
		List<ILesson> lessons = new LinkedList<ILesson>();
		lessons.add(lesson); 
		Course_DB.createCourse("tau_1", "modelim", "anna", lessons, ma, mb, "A", new TreeSet<String>());
		
		MyTime bTime2 = new MyTime(18, 10);  
		MyTime eTime2 = new MyTime(19, 10);
		Lesson lesson2 = new Lesson(1, bTime2, eTime2, "here"); 
		MyTime bTime3 = new MyTime(20, 10);  
		MyTime eTime3 = new MyTime(21, 10);
		Lesson lesson3 = new Lesson(1, bTime3, eTime3, "here"); 
		List<ILesson> lessons2 = new LinkedList<ILesson>();
		lessons2.add(lesson2); 
		lessons2.add(lesson3);
		
		Course_DB.createCourse("tau_2", "bdida", "tarsi", lessons2, ma, mb, "A", new TreeSet<String>());
		//ICourse infi = new TauCourse("tau_3", "infi", "inna", null, null, null, "A", new TreeSet<String>());
		
		           
		Course_DB.createStudent("1", "all","PIC", "2 3", "sinai gross", "cs", 4, "tau_1");
		Course_DB.createStudent("2", "all","PIC", "3 1", "paulina tsirlin", "cs", 4, "tau_2");
		Course_DB.createStudent("3", "all","PIC", "1 2", "ziv hasday", "cs", 3, "tau_1");
		
		
		*/
		
		/////
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
        /*
        Course_DB.createCourse("0366-1101-01", "Calculus 1A", "Yaron Ostrover", lessonsHedva, maHedva1, mbHedva1, "2012/A", new TreeSet<String>());
        Course_DB.createCourse("0366-1111-04", "Linear Algebra 1A", "Semyon Alesker", lessonsLinearAlgebra, maLinearAlgebra1, mbLinearAlgebra1, "2012/A", new TreeSet<String>());
        Course_DB.createCourse("0368-1118-01", "Discrete Mathematics", "Arnon Avron", lessonsBdida, maBdida, mbBdida, "2012/A", new TreeSet<String>());
        Course_DB.createCourse("0368-1105-04", "Extended Introduction To Computer Science", "Daniel Deutch, Eyal Cohen", lessonsExtendedIntro, maExtendedIntro, mbExtendedIntro, "2012/A", new TreeSet<String>());
        
       
        Course_DB.createStudent("822294292", "all","http://profile.ak.fbcdn.net/hprofile-ak-snc4/186097_822294292_2545466_q.jpg", "822294289 822294291 822294290", "Tal Gerbi", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04");
        Course_DB.createStudent("822294291", "all","http://upload.wikimedia.org/wikipedia/commons/0/02/Shari_Arison1.jpg", "822294292 822294290 822294289", "Shari Arison", "cs", 1, "0366-1101-01 0366-1111-04");
        Course_DB.createStudent("822294290", "all","http://kinderland.xnet.co.il/var/142/189979-yuval-hamebulbal.jpg", "822294289 822294292 822294291", "Yuval Hamebulbal", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04");
        Course_DB.createStudent("822294289", "all","http://www.lowdensitylifestyle.com/media/uploads/2010/10/Sigmund_Freud-loc.jpg", "822294290 822294292 822294291", "Sigmund Freud", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04");
    	*/
        
        DB_Logic.createCourse(new TauCourse("0366-1101-01", "Calculus 1A", "Yaron Ostrover", lessonsHedva, maHedva1, mbHedva1, "2012/A", new TreeSet<String>()));
        DB_Logic.createCourse(new TauCourse("0366-1111-04", "Linear Algebra 1A", "Semyon Alesker", lessonsLinearAlgebra, maLinearAlgebra1, mbLinearAlgebra1, "2012/A", new TreeSet<String>()));
        DB_Logic.createCourse(new TauCourse("0368-1118-01", "Discrete Mathematics", "Arnon Avron", lessonsBdida, maBdida, mbBdida, "2012/A", new TreeSet<String>()));
        DB_Logic.createCourse(new TauCourse("0368-1105-04", "Extended Introduction To Computer Science", "Daniel Deutch, Eyal Cohen", lessonsExtendedIntro, maExtendedIntro, mbExtendedIntro, "2012/A", new TreeSet<String>()));
        
       
        DB_Logic.createStudent(new TauStudent("822294292", "all","http://profile.ak.fbcdn.net/hprofile-ak-snc4/186097_822294292_2545466_q.jpg", "822294289 822294291 822294290", "Tal Gerbi", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04"));
        DB_Logic.createStudent(new TauStudent("822294291", "all","http://upload.wikimedia.org/wikipedia/commons/0/02/Shari_Arison1.jpg", "822294292 822294290 822294289", "Shari Arison", "cs", 1, "0366-1101-01 0366-1111-04"));
        DB_Logic.createStudent(new TauStudent("822294290", "all","http://kinderland.xnet.co.il/var/142/189979-yuval-hamebulbal.jpg", "822294289 822294292 822294291", "Yuval Hamebulbal", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04"));
        DB_Logic.createStudent(new TauStudent("822294289", "all","http://www.lowdensitylifestyle.com/media/uploads/2010/10/Sigmund_Freud-loc.jpg", "822294290 822294292 822294291", "Sigmund Freud", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04"));
    
		
		IStudent tipesh=DB_Logic.getStudent("822294290");
		System.out.println(tipesh.getName());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("HERE");  
		IStudent sinai2 = Course_DB.getStudent("822294292");
		System.out.println(sinai2.getName()); 
		List<Available> av=Course_DB.findFreeFriends(sinai2); 
		for (Available a:av){    
			System.out.println(a.getMessage());
		}   
		//Map<Integer, Map<Integer, IStudent>> result = Course_DB.findFreeFriends(sinai2);
		//System.out.println(result.size());
		 
		
		
	}
}
