
public class Date {
	private int theDay,theMonth,theYear;
	
	public int getDay(){
		return theDay;
	}
	
	public int getMonth(){
		return theMonth;
	}
	
	public int getYear(){
		return theYear;
	}
	
	public String toString(){
		return Integer.toString(theDay)+"/"+Integer.toString(theMonth)+"/"+Integer.toString(theYear);
	}
}
