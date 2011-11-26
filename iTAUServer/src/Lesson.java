
public class Lesson {
	
	
	public enum Day {
	    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, 
	    THURSDAY, FRIDAY, SATURDAY 
	}
	private Time beginTime,endTime;
	private Day lessDay;
	
	public Lesson(Day day,Time beginTime,Time endTime){
		this.lessDay=day;
		this.beginTime=beginTime;
		this.endTime=endTime;
	}
	
	public Time getBeginTime(){
		return beginTime;
	}
	public Time getEndTime()
	{
		return endTime;
	}
	public Day getDay(){
		return lessDay;
	}
}
