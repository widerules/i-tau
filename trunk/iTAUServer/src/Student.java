import java.awt.Image;


public interface Student {
	
	/* int because may be free but 'taken' by other person/activity */
	public int isFree();
	public void setFree(int free_type) throws Exception;
	public String getName();
	public void setName(String name);
	public String getUserName();
	public void setUserName(String userName);
	public String getFaculty();
	public void setFaculty(String faculty);
	public Image getImage();
	public void setImage(Image image);
	public void setYear(int year);
	public int getYear();
	public void setPassword(String password);
	public String getPassword();
	public void addFriend(Student student);
	public void removeFriend(Student student);

}
