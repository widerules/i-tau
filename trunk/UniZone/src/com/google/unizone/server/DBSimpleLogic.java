package com.google.unizone.server;

import java.util.List;

import javax.jdo.PersistenceManager;

public class DBSimpleLogic {

	public static void createStudent(ITestStudent student) {
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
			System.out.println(e.getMessage() + e.getClass().toString());
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
	
	public static void removeStudent(ITestStudent student) {
		if (student != null) {
			removeFromDB(student);
		} else {
			System.out.println("ERROR : (DB_Logic) Attempt to remove empty student");
		}
	}
	
	public static ITestStudent getStudent(String id) {      
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();  
        ITestStudent s = null;
        String query= "select from " + ITestStudent.class.getName() + " where facebookID == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<ITestStudent> student = (List<ITestStudent>) pm.newQuery(query).execute();
                if (student.size() > 0){
                        s = student.get(0);
                }
        }
        finally{
                pm.close();
        }
        return s;
	}
	
	public static Boolean checkIfStudentExist(String id) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        //List<Customer> customer = new LinkedList<Customer>();
        String query= "select from " + ITestStudent.class.getName() + " where id == '" + id +"'";
        try{
                @SuppressWarnings("unchecked")
                List<ITestStudent> student = (List<ITestStudent>) pm.newQuery(query).execute();
                if (student.size() > 0){
                        return true;
                }
        }
        finally{
                pm.close();
        }
        return false;
    }
}
