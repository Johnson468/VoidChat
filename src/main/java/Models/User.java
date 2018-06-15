package Models;

import com.mysql.cj.util.StringUtils;

/**
 * Model class for keeping track of who sent what message
 * @author Johnson468
 *
 */


public class User {
	int userId;
	String userName;
	int[] createdRooms;
	public User(int userId, String userName, int[] createdRooms) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.createdRooms = createdRooms;
	}
	public User(int userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	public User() {
		
	}
	public User(String userName) {
		this.userName = userName;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int[] getCreatedRooms() {
		return createdRooms;
	}
	public void setCreatedRooms(int[] createdRooms) {
		this.createdRooms = createdRooms;
	}
	
}
