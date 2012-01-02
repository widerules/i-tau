package com.google.unizone.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;

import org.junit.Test;

import com.google.unizone.server.Lesson;





public class DB_Logic {
	public static void updateCourse(Course course){
		//DB_Logic.removeCourse(course);
		//DB_Logic.createCourse(course);
		
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		//javax.jdo.Transaction tx = pm.currentTransaction();
		try
		{
			pm.makePersistent(course);
		} finally {
			pm.close();
		}
			/*
		    tx.begin();
			Course updatedCourse = pm.getObjectById(Course.class, course.getID());
		    updatedCourse.setCourseName(course.getCourseName());
		    updatedCourse.setSemester(course.getSemester());
		    updatedCourse.setLessonTimes(course.getLessonTimes());
		    updatedCourse.setStudentIDList(course.getStudentIDList());
		    updatedCourse.setMoedADate(course.getMoedADate());
		    updatedCourse.setMoedBDate(course.getMoedBDate());
		    
		    pm.makePersistent(updatedCourse);
		    System.out.println(JDOHelper.getPersistenceManager(updatedCourse));
		    tx.commit();
		}
		catch (Exception e)
		{
			System.out.println("ERROR : (DB_Logic - updateCourse) **** " + e.getMessage());
			
		    if (tx.isActive())
		    {
		      tx.rollback();
		    }
		}
		finally
		{
			if (tx.isActive())
		    {
		      tx.rollback();
		    }
			pm.close();
			pm=null;
		}*/
	}
	
	public static void updateStudent(Student student){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		javax.jdo.Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			Student updatedStudent = pm.getObjectById(Student.class, student.getFacebookID());
			updatedStudent.setName(student.getName());
			updatedStudent.setAccessToken(student.getAccessToken());
			updatedStudent.setFaculty(student.getFaculty());
			updatedStudent.setPic(student.getPic());
			updatedStudent.setFBFriends(student.getFBFriends());
			updatedStudent.setCourseIDList(student.getCourseIDList());
			updatedStudent.setYear(student.getYear());
			pm.makePersistent(updatedStudent);
			tx.commit();
		} catch (Exception e) {
			System.out.println("ERROR : (DB_Logic - updateStudent) **** " + e.getMessage());
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			pm.close();
		}
	}
	public static List<Student> getAllStudents() { 
		PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + Student.class.getName();
        List<Student> stus = new LinkedList<Student>();
        try{
                @SuppressWarnings("unchecked")
                List<Student> students = (List<Student>) pm.newQuery(query).execute();
                if (students.size() <= 0){
                        return null;
                }
                stus=new LinkedList<Student>();
                stus.addAll(students);
        } catch(Exception e) {
        	System.out.println("DERROR : (DB_Logic getAllStudents) " + e.getMessage());
        }
        finally{
                pm.close();
        } 
        return stus;
	}
	
	
	public static List<Course> getAllCourses() { 
		PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + Course.class.getName();
        List<Course> stus = new LinkedList<Course>();
        try{
                @SuppressWarnings("unchecked")
                List<Course> students = (List<Course>) pm.newQuery(query).execute(); 
                if (students.size() <= 0){
                        return null;
                }
                stus=new LinkedList<Course>();
                stus.addAll(students);
        } catch(Exception e) {
        	System.out.println("DERROR : (DB_Logic getAllCourses) " + e.getMessage());
        }
        finally{
                pm.close();
        } 
        return stus;
	}
	

		

	
	public static String appToString (){
		return "<A href=\"index.jsp\"><h1 id=\"appTitle\">UniZone</h1></A>";
	}
	
	
	public static String[][]  scheduleToArray (Student student) {
		List<IShiur> schedule = DB_Logic.getSchedule(student);
		String[][] SheduleArray =  new String[13][7];
		int day, StartTime, EndTime, Length;
		for (IShiur iShiur : schedule) {
			day = iShiur.getDay();
			StartTime = iShiur.getBeginTime().getHours();
			EndTime = iShiur.getEndTime().getHours();
			Length = EndTime - StartTime;
			for (int i=0;i<Length;i++){
				SheduleArray[StartTime-7+i][day] = iShiur.nameToString();
			}

		}
		return SheduleArray;
	}

	
	public static String  scheduleToString (Student student) {
		String[][] scheduleArray=scheduleToArray(student);
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
				if (scheduleArray[i][j] != null){
					if (scheduleArray[i][j].length() >= 10){
						output += "<a rel=\"htmltooltip\">" + scheduleArray[i][j].substring(0, 10) + "</a>";
						output += "<div class=\"htmltooltip\">" + scheduleArray[i][j] + "</div>";
					}
					else{
						output += scheduleArray[i][j];
					}
				}
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
	
	////////CHECK IF WANT ALL THE PARAMS
	public static void createCourse(Course course) {
		if (course != null) {
			storeToDB(course);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to create empty course");
		}
	}
	
	public static void createStudent(Student student) {
		if (student != null) {
			storeToDB(student);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to create empty student");
		}
	}
	
	
	public static void storeToDB(Object object) {
			PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(object);
		} catch(Exception e) {
			if (pm == null) {
				System.out.println("HERE");
			} else {
				System.out.println(pm.toString());
			}
			System.out.println("storeToDB ERROR "+e.getMessage() + e.getClass().toString());
			
		} finally {
			pm.close();
		}
	}
	
	public static void removeStudent(Student student) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.currentTransaction().begin();
            student = pm.getObjectById(Student.class, student.getFacebookID());
            if (student==null){
            	return;
            }
            pm.deletePersistent(student);
            pm.currentTransaction().commit();
        } catch (Exception ex) {
            pm.currentTransaction().rollback();
        } finally {
            pm.close();
        }
	}	
	
	public static void removeStudent(String stu) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.currentTransaction().begin();
            Student student = pm.getObjectById(Student.class, stu);
            if (student==null){
            	return;
            }
            pm.deletePersistent(student);
            pm.currentTransaction().commit();
        } catch (Exception ex) {
            pm.currentTransaction().rollback();
            //throw new RuntimeException(ex);
        } finally {
            pm.close();
        }
	}
	
	
	
	public static void removeCourse(Course course) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.currentTransaction().begin();
			// We don't have a reference to the selected Product.
			// So we have to look it up first, 
			course = pm.getObjectById(Course.class, course.getID());
			pm.deletePersistent(course);
			pm.currentTransaction().commit();
			} catch (Exception ex) {
				pm.currentTransaction().rollback();
				throw new RuntimeException(ex);
		} finally {
				pm.close();
		} 
	}
	
	/**
     * getStudent
     * @return Student whose id equals a given id. 
     */
	public static void cleanDB(){
		removeStudent("690329329");
		removeStudent("100003288956124");
		removeStudent("591867536");
	}
	
	
    public static Student getStudent(String id) {      
    		//removeStudent("690329329");
    		//removeStudent("100003288956124");
            PersistenceManager pm = PMF.get().getPersistenceManager(); 
            //List<Customer> customer = new LinkedList<Customer>();  
            Student s = null, detached = null;
            String query= "select from " + Student.class.getName() + " where facebookID == '" + id +"'";
            try{
                    @SuppressWarnings("unchecked")
                    List<Student> student = (List<Student>) pm.newQuery(query).execute();
                    if (student.size() > 0){
                            s = student.get(0);
                            detached = pm.detachCopy(s);
                    }
            }catch (Exception e){
            	return null;
            }
            finally{
                    pm.close();
            }
            //return s;
            return detached;
    }
      
    public static Course getCourse(String id) {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	pm.getFetchPlan().addGroup("lessons");
    	//pm.getFetchPlan().addGroup("times");
        Course c = null, detached = null;
        String query= "select from " + Course.class.getName() + " where courseID == '" + id +"'";
        try{
        		
        	
        	@SuppressWarnings("unchecked")
            List<Course> course= (List<Course>) pm.newQuery(query).execute();
            if (course.size() > 0){
                    c = course.get(0);
                    c.getLessonTimes();
                    detached = pm.detachCopy(c);
        	//pm.currentTransaction().begin();
               // c = pm.getObjectById(Course.class, id);
               // pm.currentTransaction().commit();
            }
        }
        catch (Exception e) {
			System.out.println("shit!! " + e.getMessage());
			return null;
		//	if (pm.currentTransaction().isActive())
		 //   {
		//		pm.currentTransaction().rollback();
		 //   }
			
		}
        finally{
                pm.close();
                
        }
        //return c;
        return detached;
    }
               
    //@SuppressWarnings("deprecation")
    public static List<Student> getCourseStudents(Course course){
    		
    	
    	
    	
    	
    		List<Student> courseStudents = new LinkedList<Student>();
           // @SuppressWarnings("unchecked")
			Set<String> studentsIds = course.getStudents();
            if(courseStudents.size() == 0){
                    return courseStudents;
            }
            PersistenceManager pm = PMF.get().getPersistenceManager();
            
            try {
                for (String id : studentsIds){   
                	
                	Student stu=getStudent(id);
            		courseStudents.add(stu);
            		//if the above 2 line oesnt work, change with:
                        /*ME: String query= "select from " + IStudent.class.getName() + " where id == '" + id + "'";
                        @SuppressWarnings("unchecked")
                        List<Event> eventsDB = (List<Event>) pm.newQuery(query).execute();
                        if(eventsDB.size() > 0){
                        	events.add(eventsDB.get(0));
                                
                        }*/
            			// e = pm.getObjectById(Event.class, eid);                                                                               
                }
            }
            finally{
                    pm.close();
            }


            return courseStudents;
    }
    
    //@SuppressWarnings("unchecked")   
    public static List<IShiur> getSchedule(Student stu){
		List<Course> courses=getStudentCourses(stu);
		List<IShiur> shiurim=new LinkedList<IShiur>();
    	for (Course course : courses){
    		//@SuppressWarnings("unchecked")
			List<Lesson> lessons=course.getLessonTimes();
    		for (Lesson lesson : lessons){
    			shiurim.add(new Shiur(lesson.getDay(), lesson.getBeginTime(), lesson.getEndTime(), course.getID(), course.getCourseName()));
    		}
    	}
    	Collections.sort(shiurim, new ShiurComparator());
    	return shiurim;
    }
    
   // @SuppressWarnings("deprecation")
    public static List<Course> getStudentCourses(Student student){
    	//@SuppressWarnings("unchecked")
		Set<String> coursesIds = student.getCourseIDList();
		List<Course> studentCourses = new LinkedList<Course>();      
        if(coursesIds.size() == 0){
                return studentCourses;
        }
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        try {
            for (String id : coursesIds){   
            	
            	Course c=getCourse(id);
            	studentCourses.add(c);
        		//if the above 2 line oesnt work, change with:
                    /*ME: String query= "select from " + IStudent.class.getName() + " where id == '" + id + "'";
                    @SuppressWarnings("unchecked")
                    List<Event> eventsDB = (List<Event>) pm.newQuery(query).execute();
                    if(eventsDB.size() > 0){
                    	events.add(eventsDB.get(0));
                            
                    }*/
            		
            		
        			// e = pm.getObjectById(Event.class, eid);                         
                                    
            
            }
        }
        finally{
                pm.close();
        }


        return studentCourses;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Boolean checkIfCourseExist(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + Course.class.getName() + " where courseID == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<Course> course = (List<Course>) pm.newQuery(query).execute();
                if (course.size() > 0){
                        return true;
                }
        }
        finally{
                pm.close();
        }
        return false;
    }
    
    public static Boolean checkIfStudentExist(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + Student.class.getName() + " where facebookID == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<Student> student = (List<Student>) pm.newQuery(query).execute();
                if (student.size() > 0){
                        return true;
                }
        }
        finally{
                pm.close();
        }
        return false;
    }
    
    //////////////////////////////////////////////////////////////////////////
    
    public static void addCourseToStudet(Student stu, Course course) {
    	stu.addCourseID(course.getID());
    	updateStudent(stu);
    }
    
    public static void addStudentToCourse(Student stu, Course course) {
    	course.addStudent(stu.getFacebookID());
    	updateCourse(course);
    }
    
       
    public static void RemoveCourseFromStudet(Student stu, Course course) {
    	stu.removeCourseID(course.getID());
    	updateStudent(stu);
    }
    
    public static void removeStudentFromCourse(Student stu, Course course) {
    	course.removeStudent(stu.getFacebookID());
    	updateCourse(course);
    }
    
    /* 
	 * this method return free friends in the format of:
	 * key=facebookID, value=hour which he is free until
	 */
	public static List<Available> findFreeFriends(Student student) { 
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
			Student ISfriend = getStudent(friend);
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

	public static String checkIfStudentFree(Student student) { 
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
	public static String availabilityMessage(Student student,int[] times) { 
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
	public static int[] isFree(Student student, int curr_day, int curr_hour) { 
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
}
