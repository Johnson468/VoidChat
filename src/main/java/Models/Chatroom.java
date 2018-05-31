package Models;

import java.util.ArrayList;

public class Chatroom {
	int maxMembers;
	int currentMembers;
	int roomId;
	
	public Chatroom(int maxMembers, int currentMembers, int roomId) {
		super();
		this.maxMembers = maxMembers;
		this.currentMembers = currentMembers;
		this.roomId = roomId;
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
}
