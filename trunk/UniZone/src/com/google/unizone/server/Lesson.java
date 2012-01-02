package com.google.unizone.server;


import javax.jdo.annotations.Embedded;
import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/*
 * this class expresses a session of a course
 * example : the course descrete math is taught 
 * on sunday 10-12 and on monday 12-14.
 * each one of those is called a lesson.
 */

//(identityType = IdentityType.APPLICATION, detachable = "true")
@PersistenceCapable(detachable = "true")
//@EmbeddedOnly
//@FetchGroup(name = "times", members = { @Persistent(name = "beginTime") })
public class Lesson{

	
	/*public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	    THURSDAY, FRIDAY, SATURDAY 
	}*/
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value= "true")
	private String lessonKey;
	
	@Persistent(defaultFetchGroup="true")
	@Embedded
	private MyTime beginTime;
	
	@Persistent(defaultFetchGroup="true")
	@Embedded
	private MyTime endTime;
	
	@Persistent
	private int lessDay;

	
	public Lesson(int day,MyTime beginTime,MyTime endTime){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
	}
	public int getDay(){
		return lessDay;
	}
	
	public MyTime getBeginTime(){
		return beginTime;
	}
	public MyTime getEndTime(){
		return endTime;
	}
	
	
	public String getKey(){
		return this.lessonKey;
	}
}
