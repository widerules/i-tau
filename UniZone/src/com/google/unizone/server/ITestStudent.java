package com.google.unizone.server;

import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Inheritance;

@PersistenceCapable

public class ITestStudent{
	
	@PrimaryKey
	@Persistent
	protected String facebookID;
	
	@Persistent
	protected String name;
	
	public String getFacebookID() {
		return this.facebookID;
	}
	
	public String getName() {
		return this.name;
	}
}
