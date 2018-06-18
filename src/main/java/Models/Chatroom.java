package Models;

public class Chatroom {
	int maxMembers;
	int currentMembers;
	int roomId;
	String key;
	
	public Chatroom(int maxMembers, int currentMembers, int roomId) {
		super();
		this.maxMembers = maxMembers;
		this.currentMembers = currentMembers;
		this.roomId = roomId;
	}
	
	public Chatroom(int maxMembers, int currentMembers, int roomId, String key) {
		super();
		this.maxMembers = maxMembers;
		this.currentMembers = currentMembers;
		this.roomId = roomId;
		this.key = key;
	}

	public Chatroom(int maxMembers, int roomId, String key) {
		super();
		this.maxMembers = maxMembers;
		this.currentMembers = currentMembers;
		this.roomId = roomId;
		this.key = key;
	}

	public Chatroom(int maxMembers, int roomId) {
		super();
		this.maxMembers = maxMembers;
		this.roomId = roomId;
	}
	public Chatroom() {
	}
	public int getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}
	public int getCurrentMembers() {
		return currentMembers;
	}
	public void setCurrentMembers(int currentMembers) {
		this.currentMembers = currentMembers;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
