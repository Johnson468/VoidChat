package Utilities;

import java.util.ArrayList;

public class MessageManager {
	
	ArrayList<String> roomMessages;
	int roomId;
	
	
	
	public MessageManager(int roomId) {
		super();
		this.roomId = roomId;
	}
	public ArrayList<String> getRoomMessages() {
		return roomMessages;
	}
	public void setRoomMessages(ArrayList<String> roomMessages) {
		this.roomMessages = roomMessages;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	
	
	
	
}
