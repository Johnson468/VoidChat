package Utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Message;

public class MessageManager {
	
	ArrayList<String> roomMessages;
	int roomId;
	
	public MessageManager(int roomId) {
		super();
		this.roomId = roomId;
	}
	public MessageManager() {
		
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
	
	public ArrayList<Message> getMessages(int roomId) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Message> messages = new ArrayList<Message>();
		Connection conn = ConnectionManager.makeConnection();
		
		try {
		    stmt = conn.createStatement();
		    //Query all rooms in the database
		    rs = stmt.executeQuery("SELECT * FROM messages WHERE room_id = " + String.valueOf(roomId) + ";");
		    if (rs.next() == false) {
		    	return null;
		    }
		    // Go through the results and see if the session has the chatrooms or not
		    while(rs.next()) {
		    	messages.add(new Message(rs.getString("contents"), rs.getString("sender")));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			    if (rs != null) {
			        try {
			            rs.close();
			        } catch (SQLException sqlEx) { } // ignore

			        rs = null;
			    }

			    if (stmt != null) {
			        try {
			            stmt.close();
			        } catch (SQLException sqlEx) { } // ignore

			        stmt = null;
			    }
		}
		return messages;
		
	}
}
