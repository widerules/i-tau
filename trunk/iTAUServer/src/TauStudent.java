import java.awt.Image;
import java.util.List;

public class TauStudent implements Student {
	
	private int isFree;
	private String name;
	private String userName;
	private String password;
	private String faculty;
	private int year;
	private Image picture;
	private List<TauStudent> friends_list;
	
	public TauStudent(String userName, String password, String name, String faculty, int year, Image picture) {
		this.userName = userName;
		this.password = password;
		this.faculty = faculty;
		this.name = name;
		this.year = year;
		this.picture = picture;
	}
	
	public int isFree() {
		return this.isFree;
	}
	
	/* 0 - free
	 * 1 - free but 'taken'
	 * 2 - not free
	 * 3 - not in university
	 */
	public void setFree(int free_type) throws Exception {
		if (free_type > 3 || free_type < 0) {
			Exception up =  new Exception("ERROR : Value of free_type must be an integet 0-3\nC");
			throw up;
		}
		this.isFree = free_type;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFaculty() {
		return this.faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public Image getImage() {
		return this.picture;
	}
	public void setImage(Image picture) {
		this.picture = picture;
	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void addFriend(Student student) {
		this.friends_list.add((TauStudent) student);
	}
	public void removeFriend(Student student) {
		this.friends_list.remove(student);
	}
}
