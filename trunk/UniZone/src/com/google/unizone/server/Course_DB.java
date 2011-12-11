package com.google.unizone.server;


//import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


//import java.util.TreeSet;

import com.google.unizone.server.TauCourse;
import com.google.unizone.server.TauStudent;


public class Course_DB {

	private static HashMap<String, ICourse> course_hash = new HashMap<String, ICourse>();
	private static HashMap<String, IStudent> student_hash = new HashMap<String, IStudent>();



	public static String appToString (){
		return "<A href=\"ITAUApp6.jsp\"><h1 id=\"appTitle\">UniZone</h1></A>";
	}

	public static String[][]  scheduleToArray (IStudent student) {
		List<IShiur> schedule = Course_DB.getSchedule(student);
		String[][] SheduleArray =  new String[13][7];
		int day, StartTime, EndTime, Length;
		for (IShiur iShiur : schedule) {
			day = iShiur.getDay();
			StartTime = iShiur.getBeginTime().getHours();
			EndTime = iShiur.getEndTime().getHours();
			Length = EndTime - StartTime;
			for (int i=0;i<Length;i++){
				SheduleArray[StartTime-7+i][day] = iShiur.getCourseName();
			}

		}
		return SheduleArray;
	}

	public static String  scheduleToString (IStudent student) {
		String[][] scheduleArray  =scheduleToArray(student);
		String output = "<br>";
		String iShiurName = "";
		int columnWidth = 20;
		int shiurWidth;
		int Spaces2Add;
		scheduleArray[0][1] = "Sunday";
		scheduleArray[0][2] = "Monday";
		scheduleArray[0][3] = "Tuesday";
		scheduleArray[0][4] = "Wednesday";
		scheduleArray[0][5] = "Thursday";
		scheduleArray[0][6] = "Friday";
		scheduleArray[1][0] = "8:00";
		scheduleArray[2][0] = "9:00";
		scheduleArray[3][0] = "10:00";
		scheduleArray[4][0] = "11:00";
		scheduleArray[5][0] = "12:00";
		scheduleArray[6][0] = "13:00";
		scheduleArray[7][0] = "14:00";
		scheduleArray[8][0] = "15:00";
		scheduleArray[9][0] = "16:00";
		scheduleArray[10][0] = "17:00";
		scheduleArray[11][0] = "18:00";
		scheduleArray[12][0] = "19:00";


		output += "<table id=\"schedule\"";
		for (int i = 0;i<scheduleArray.length;i++){
			output += "<tr>";
			for (int j=0;j<scheduleArray[i].length;j++){
				if(i == 0 || j == 0)
					output += "<td id=\"header\">";
				else
					output += "<td>";
				if (scheduleArray[i][j] != null)
					output += scheduleArray[i][j];
				output += "</td>";
			}
			output += "</tr>";
		}
		output += "</table>";
		return output;
	}

	private String createHtmlTable()
	{
		/*
		for (int i = 0;i<scheduleArray.length;i++){
			for (int j=0;j<scheduleArray[0].length;j++){
				if (scheduleArray[i][j] == null){
					shiurWidth = 0;
					iShiurName = "";
				}
				else {
					iShiurName = scheduleArray[i][j];
					shiurWidth = scheduleArray[i][j].length();
				}
				if (shiurWidth > columnWidth){
					iShiurName = iShiurName.substring(0, columnWidth-1);
				}
				else {
					Spaces2Add = columnWidth - shiurWidth;
					output += iShiurName;
					for (int f=0;f<Spaces2Add;f++){
						output += "&nbsp";
					}
				}
			}
			output += "<br>";
		}
		 */
		/*
		for (int i = 0;i<scheduleArray.length;i++){
			for (int j=0;j<scheduleArray[0].length;j++){
				if (scheduleArray[i][j] == null){
					shiurWidth = 0;
					iShiurName = "";
				}
				else {
					iShiurName = scheduleArray[i][j];
					shiurWidth = scheduleArray[i][j].length();
				}
				if (shiurWidth > columnWidth){
					iShiurName = iShiurName.substring(0, columnWidth-1);
				}
				else {
					Spaces2Add = columnWidth - shiurWidth;
					output += iShiurName;
					for (int f=0;f<Spaces2Add;f++){
						output += "&nbsp";
					}
				}
			}
			output += "<br>";
		}
	}
		 */
		return null;
	}




	public static void startSimulation(){

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

		createCourse("0366-1101-01", "Calculus 1A", "Yaron Ostrover", lessonsHedva, maHedva1, mbHedva1, "2012/A", new TreeSet<String>());
		createCourse("0366-1111-04", "Linear Algebra 1A", "Semyon Alesker", lessonsLinearAlgebra, maLinearAlgebra1, mbLinearAlgebra1, "2012/A", new TreeSet<String>());
		createCourse("0368-1118-01", "Discrete Mathematics", "Arnon Avron", lessonsBdida, maBdida, mbBdida, "2012/A", new TreeSet<String>());
		createCourse("0368-1105-04", "Extended Introduction To Computer Science", "Daniel Deutch, Eyal Cohen", lessonsExtendedIntro, maExtendedIntro, mbExtendedIntro, "2012/A", new TreeSet<String>());

		Course_DB.createStudent(
				"822294292", "all","http://profile.ak.fbcdn.net/hprofile-ak-snc4/186097_822294292_2545466_q.jpg", "822294289 822294291 822294290", "Tal Gerbi", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04"); 
				Course_DB.createStudent(
				"822294291", "all","http://upload.wikimedia.org/wikipedia/commons/0/02/Shari_Arison1.jpg", "822294292 822294290 822294289", "Shari Arison", "cs", 1, "0366-1101-01 0366-1111-04"); 
				Course_DB.createStudent(
				"822294290", "all","http://kinderland.xnet.co.il/var/142/189979-yuval-hamebulbal.jpg", "822294289 822294292 822294291", "Yuval Hamebulbal", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04"); 
				Course_DB.createStudent(
				"822294289", "all","http://www.lowdensitylifestyle.com/media/uploads/2010/10/Sigmund_Freud-loc.jpg", "822294290 822294292 822294291", "Sigmund Freud", "cs", 1, "0366-1101-01 0366-1111-04 0368-1118-01 0368-1105-04");	}

	public static void createCourse(String courseID, String courseName, String professorName, List<ILesson> lessonTimes, MyDate moedADate, MyDate moedBDate, String semester, Set<String> studentIDList){
		TauCourse course=new TauCourse(courseID, courseName, professorName, lessonTimes, moedADate, moedBDate, semester, studentIDList);
		course_hash.put(courseID, course);
	}
	public static void createStudent(String facebookID, String accessToken, String picUrl, String friends, String name, String faculty, int year, String courseIDList) {
		TauStudent stu= new TauStudent(facebookID,accessToken,picUrl,friends, name,faculty,year,courseIDList);
		student_hash.put(facebookID, stu);
		Set<String> cur=stu.getCourseIDList();
		for (String s:cur){
			ICourse c=course_hash.get(s);
			c.addStudent(facebookID);
			course_hash.put(s, c);
		}

	}

	public static void createCourse(ICourse course) {
		course_hash.put(course.getID(), course);
	}
	public static void createStudent(IStudent student) {
		student_hash.put(student.getFacebookID(), student);
	}

	public static ICourse getCourse(String courseID) {
		return course_hash.get(courseID);
	}
	public static IStudent getStudent(String facebookID) {
		return student_hash.get(facebookID);
	}
	//@SuppressWarnings("unchecked")
	public static List<IStudent> getCourseStudents(ICourse course) {
		Set<String> studetIDs =course.getStudents();
		List<IStudent> result = new LinkedList<IStudent>();
		for (String studentID : studetIDs) {
			IStudent student = getStudent(studentID);
			result.add(student);
		}
		return result;
	}
	//@SuppressWarnings("unchecked")
	public static List<ICourse> getStudentCourses(IStudent student){
		Set<String> courseIDs = student.getCourseIDList();
		List<ICourse> result = new LinkedList<ICourse>();
		for (String courseID : courseIDs) {
			ICourse course = getCourse(courseID);
			result.add(course);
		}
		return result;
	}

	public static void removeStudent(IStudent student) {
		if (student != null) {
			student_hash.remove(student.getFacebookID());
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty student");
		}
	}

	public static void removeCourse(ICourse course) {
		if (course != null) {
			course_hash.remove(course.getID());
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty course");
		}
	}

	// @SuppressWarnings("unchecked")
	public static List<IShiur> getSchedule(IStudent stu){
		List<ICourse> courses=getStudentCourses(stu);
		List<IShiur> shiurim=new LinkedList<IShiur>();
		for (ICourse course : courses){
			//@SuppressWarnings("unchecked")
			List<ILesson> lessons=course.getLessonTimes();
			for (ILesson lesson : lessons){
				shiurim.add(new Shiur(lesson.getDay(), lesson.getBeginTime(), lesson.getEndTime(), lesson.getPlace(), course.getID(), course.getCourseName(), course.getProfessor()));
			}
		}
		Collections.sort(shiurim, new ShiurComparator());
		return shiurim;
	}

	public static Boolean checkIfCourseExist(String id) {
		return course_hash.containsKey(id);
	}

	public static Boolean checkIfStudentExist(String id) {
		return student_hash.containsKey(id);
	}

	/* 
	 * this method return free friends in the format of:
	 * key=facebookID, value=hour which he is free until
	 */
	public static List<Available> findFreeFriends(IStudent student) { 
		Set<String> fbFriends = student.getFBFriends(); 
		//check NULL 
		//Map<Integer, Map<Integer,IStudent>> result = new TreeMap<Integer, Map<Integer,IStudent>>(); 
		List<Available> res= new LinkedList<Available>(); 
		int[] times = new int[2]; 
		Calendar cal = 
				new GregorianCalendar(); 
		int curr_day = cal.get(Calendar.DAY_OF_WEEK); 
		int curr_hour = cal.get(Calendar.HOUR); 
		if (cal.get(Calendar.AM_PM) != 0) { 
			curr_hour += 12;
		}
		for (String friend : fbFriends) { 
			IStudent ISfriend = getStudent(friend);
			if (ISfriend == null) { 
				continue; 
			}
			times = isFree(ISfriend, curr_day, curr_hour);
			String message=availabilityMessage(ISfriend, times);
			
			Available av= new Available(ISfriend, times[0], times[1], message); 
			res.add(av);
		}
		Collections.sort(res, new AvailableComperator()); 
		return res; 
	}

	public static String checkIfStudentFree(IStudent student) { 
		Calendar cal = 
				new GregorianCalendar(); 
		int curr_day = cal.get(Calendar.DAY_OF_WEEK); 
		int curr_hour = cal.get(Calendar.HOUR); 
		if (cal.get(Calendar.AM_PM) != 0) { 
			curr_hour += 12;
		}
		int[] times = new int[2]; 
		times = isFree(student, curr_day, curr_hour);
		return availabilityMessage(student, times); 
	}
	public static String availabilityMessage(IStudent student,int[] times) { 
		String message = 
				""; 
		if (times[0] == 0){ 
			message =
					/*student.getName() + */" is available now until " + times[1] + ":00"; 
		}
		else if (times[0]==100 && times[1] == 24){ 
			message =
					/*student.getName() + */" is not studying today"; 
		} 
		else if (times[1] == 24){ 
			message =
					/*student.getName() + */" is available from " + times[0] + ":00 until the end of the day"; 
		} 
		else { 
			message = 
					/*student.getName() + */" is available from " + times[0] + ":00 until " + times[1] + ":00"; 
		}
		return message; 
	}
	/* 
	 * method returns times from which student is free until which time,
	 * per current day. if key of returned map is 0, it means that he 
	 * currently free until time in value. if value returned is 0, means
	 * student finishes studying at time in key (and has no free hours).
	 * else - student is free from $key until $value 
	 */
	public static int[] isFree(IStudent student, int curr_day, int curr_hour) { 
		List<IShiur> schedule = getSchedule(student);
		//Map<Integer, Integer> result = new HashMap<Integer, Integer>(); 
		int[] result = new int[2]; 
		//dummy value which cannot represent an hour 
		int lastEndTime = 100; 
		for (IShiur shiur : schedule) { 
			if (shiur.getDay() == curr_day) { 
				if (shiur.getBeginTime().getHours() <= curr_hour && shiur.getEndTime().getHours() > curr_hour) { 
					//In Class NOW 
					lastEndTime = shiur.getEndTime().getHours();
					result[0] = lastEndTime;
					result[1] = 24;
					continue; 
				}
				if (shiur.getEndTime().getHours() <= curr_hour) { 
					//Earlier class TODAY 
					lastEndTime=shiur.getEndTime().getHours();
					result[0] = lastEndTime;
					result[1] = 24;
					continue; 
				}
				if (shiur.getEndTime().getHours() >= curr_hour && lastEndTime==100){ 
					lastEndTime=shiur.getEndTime().getHours();
					result[0] = lastEndTime;
					result[1] = 24;
					continue; 
				}
				if (shiur.getBeginTime().getHours() > lastEndTime) { 
					//found free hour 
					if (curr_hour >= lastEndTime){ 
						//FREE NOW 
						//result.put(0, shiur.getBeginTime().getHours()); 
						result[0] = 0;
						result[1] = shiur.getBeginTime().getHours();
						break; 
					}
					else{ 
						//FREE LATER TODAY 
						//result.put(lastEndTime, shiur.getBeginTime().getHours()); 
						result[0] = lastEndTime;
						result[1] = shiur.getBeginTime().getHours();
						break; 
					}
				}
			}
			if (shiur.getDay() > curr_day) { 
				//FINISHED SRUDYING TODAY OR NOT STUDYING AT ALL TODAY OR HAS NO GAPS TODAY 
				//result.put(lastEndTime, 0); 
				//finished studying or not studying today 
				result[0] = lastEndTime;
				result[1] = 24;
				break; 
			}
		}
		return result; 
	}

	/*public static void addCourseToStudet(IStudent stu, ICourse course) {
	    	stu.addCourseID(course.getID());
	    	createStudent(stu);
	    }

	    public static void addStudentToCourse(IStudent stu, ICourse course) {
	    	course.addStudent(stu.getFacebookID());
	    	createCourse(course);
	    }


	    public static void RemoveCourseFromStudet(IStudent stu, ICourse course) {
	    	stu.removeCourseID(course.getID());
	    	createStudent(stu);
	    }

	    public static void removeStudentFromCourse(IStudent stu, ICourse course) {
	    	course.removeStudent(stu.getFacebookID());
	    	createCourse(course);
	    }
	 */
}
