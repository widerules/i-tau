package com.google.unizone.server;



import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUPERCLASS_TABLE)
public class TestStudet extends ITestStudent  {
	
	
	@Persistent
	private int x;
	
	public TestStudet(String facebookID,String name) {
		
		this.name = name;
		
		this.facebookID = facebookID;
		
	}
	
	
	
	
}
