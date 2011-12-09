package com.google.code.iTau;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.jdo.PersistenceManager;

import com.google.code.iTau.ILesson;


public class DB_Logic {
		
	public static void createStudent(IStudent student) {
		if (student != null) {
			storeToDB(student);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to create empty student");
		}
	}
	
	public static void createCourse(ICourse course) {
		if (course != null) {
			storeToDB(course);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to create empty course");
		}
	}
	
	public static void storeToDB(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(object);
		} finally {
			pm.close();
		}
	}
	
	
	
	public static void removeFromDB(Object object) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.deletePersistent(object);
		} finally {
			pm.close();
		}
	}
	
	public static void removeStudent(IStudent student) {
		if (student != null) {
			removeFromDB(student);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty student");
		}
	}
	
	public static void removeCourse(ICourse course) {
		if (course != null) {
			removeFromDB(course);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty course");
		}
	}
	
	/**
     * getStudent
     * @return Student whose id equals a given id. 
     */
    public static IStudent getStudent(String id) {
            PersistenceManager pm = PMF.get().getPersistenceManager();
            //List<Customer> customer = new LinkedList<Customer>();
            IStudent s = null;
            String query= "select from " + IStudent.class.getName() + " where id == '" + id +"'";
            try{
                    @SuppressWarnings("unchecked")
                    List<IStudent> student = (List<IStudent>) pm.newQuery(query).execute();
                    if (student.size() > 0){
                            s = student.get(0);
                    }
            }
            finally{
                    pm.close();
            }
            return s;
    }
          
               
    //@SuppressWarnings("deprecation")
    public static List<IStudent> getCourseStudents(ICourse course){
    
    		List<IStudent> courseStudents = new LinkedList<IStudent>();
           // @SuppressWarnings("unchecked")
			Set<String> studentsIds = course.getStudents();
            if(courseStudents.size() == 0){
                    return courseStudents;
            }
            PersistenceManager pm = PMF.get().getPersistenceManager();
            
            try {
                for (String id : studentsIds){   
                	
                	IStudent stu=getStudent(id);
            		courseStudents.add(stu);
            		//if the above 2 line oesnt work, change with:
                        /*ME: String query= "select from " + IStudent.class.getName() + " where id == '" + id + "'";
                        @SuppressWarnings("unchecked")
                        List<Event> eventsDB = (List<Event>) pm.newQuery(query).execute();
                        if(eventsDB.size() > 0){
                        	events.add(eventsDB.get(0));
                                
                        }*/
                        //TODO: not good
            			// e = pm.getObjectById(Event.class, eid);                                                                               
                }
            }
            finally{
                    pm.close();
            }


            return courseStudents;
    }
    
      
    public static ICourse getCourse(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        ICourse c = null;
        String query= "select from " + ICourse.class.getName() + " where id == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<ICourse> course = (List<ICourse>) pm.newQuery(query).execute();
                if (course.size() > 0){
                        c = course.get(0);
                }
        }
        finally{
                pm.close();
        }
        return c;
    }
    
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
    
   // @SuppressWarnings("deprecation")
    public static List<ICourse> getStudentCourses(IStudent student){
    	//@SuppressWarnings("unchecked")
		Set<String> coursesIds = student.getCourseIDList();
		List<ICourse> studentCourses = new LinkedList<ICourse>();      
        if(coursesIds.size() == 0){
                return studentCourses;
        }
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        try {
            for (String id : coursesIds){   
            	
            	ICourse c=getCourse(id);
            	studentCourses.add(c);
        		//if the above 2 line oesnt work, change with:
                    /*ME: String query= "select from " + IStudent.class.getName() + " where id == '" + id + "'";
                    @SuppressWarnings("unchecked")
                    List<Event> eventsDB = (List<Event>) pm.newQuery(query).execute();
                    if(eventsDB.size() > 0){
                    	events.add(eventsDB.get(0));
                            
                    }*/
            		
            		
                    //TODO: not good
        			// e = pm.getObjectById(Event.class, eid);                         
                                    
            
            }
        }
        finally{
                pm.close();
        }


        return studentCourses;
    }
    
    
    public static Boolean checkIfCourseExist(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + ICourse.class.getName() + " where id == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<ICourse> course = (List<ICourse>) pm.newQuery(query).execute();
                if (course.size() > 0){
                        return true;
                }
        }
        finally{
                pm.close();
        }
        return false;
    }
    
    public static void addCourseToStudet(IStudent stu, ICourse course) {
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
    
    
}
