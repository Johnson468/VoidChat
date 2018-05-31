/**
 * @author Johnson468
 * 
 * Todo:
 * Fix the join chat by ID
 * Display messages by chatroom
 * Implement encryption
 * Make it not ugly
 */

package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.log4j.Logger;
import Models.Chatroom;

public class ChatroomManager {
	Logger logger = Logger.getLogger(this.getClass());
	ArrayList<Chatroom> rooms;
	
	public ChatroomManager() {
		rooms = new ArrayList<Chatroom>();
	}
	/**
	 * Query the database and return a Chatroom based on the ID
	 * @param id The id to search for (Primary key in the chatroom table)
	 * @return 
	 */
	public Chatroom getRoomById(int id) {
		//Make sure the session has the most updated chatrooms
				Statement stmt = null;
				ResultSet rs = null;
				Connection conn = ConnectionManager.makeConnection();
				int roomId;
				int maxMembers = 0;
				int currentMembers = 0;
				try {
				    stmt = conn.createStatement();
				    //Query all rooms in the database
				    rs = stmt.executeQuery("SELECT * FROM chatroom WHERE room_id = " + String.valueOf(id) + ";");
				    if (rs.next() == false) {
				    	return null;
				    }
				    // Go through the results and see if the session has the chatrooms or not
				    while(rs.next()) {
				    	roomId = rs.getInt("room_id");
				    	maxMembers = rs.getInt("max_members");
				    	currentMembers = rs.getInt("curr_members");
				    }
				}
				catch (SQLException ex){
				    // handle any errors
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				}
				finally {
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
				return new Chatroom(maxMembers, currentMembers, id );
	}
	
	
	/**
	 * Store a chatroom in both the chatroom manager and the database
	 * @param maxMembers max number of members to be allowed in the chatroom
	 * @return The newly created Chatroom
	 */
	public Chatroom createChatroom(int maxMembers) {
		SecureRandom sr = new SecureRandom();
		int roomId = Math.abs(sr.nextInt());
		Chatroom newRoom = new Chatroom(maxMembers,roomId);
		rooms.add(newRoom);
		storeRoom(newRoom);
		return newRoom;
	}
	//Remove 
	public void deleteRoom(int id) {
		removeRoom(id);
	}
	//Not being used
	public void updateRooms() {

		//Make sure the session has the most updated chatrooms
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = ConnectionManager.makeConnection();
		
		try {
		    stmt = conn.createStatement();
		    //Query all rooms in the database
		    rs = stmt.executeQuery("SELECT * FROM chatroom");
		    
		    // Go through the results and see if the session has the chatrooms or not
		    while(rs.next()) {
		    	int roomId = rs.getInt("room_id");
		    	int maxMembers = rs.getInt("max_members");
		    	if(getRoomById(roomId) != null && getRoomById(roomId) == new Chatroom(maxMembers,roomId)) {
		    		System.out.println("Room is up to date");
		    	} else {
		    		int[] currentMembers = getMembers(roomId);
		    		//rooms.add(new Chatroom(maxMembers, currentMembers.length, currentMembers, roomId ));
		    	}
		    	
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
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
		//Update
	}
	public int[] getMembers(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		int[] member_ids = new int[10];
		Connection conn;
		//Try to make a connection
		conn = ConnectionManager.makeConnection();
		try {
		    stmt = conn.createStatement();
		    //Set up the statement 
		    rs = stmt.executeQuery("SELECT max_members, member_ids FROM chatroom WHERE room_id = " + id);
		    
		    int counter = 0;
		    //Take the results and add the members to an array
		    while(rs.next()) {
		    	member_ids[counter] = rs.getInt("member_ids");
		    	counter++;
		    }
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

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
		return member_ids;
	}
	/**
	 * Store the chatroom data in the chatrooms table
	 * @param cr The chatroom object to pull data from for storage
	 */
	public void storeRoom(Chatroom cr) {
		Statement stmt = null;
		Connection conn;
		
		conn = ConnectionManager.makeConnection();
		
		try {
		    stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO chatroom VALUES(" + String.valueOf(cr.getRoomId()) + "," + 
			String.valueOf(cr.getMaxMembers()) + ", 0);");
		  
		    
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	/**
	 * Remove a room from the database, mostly to be used when the room is empty and no longer being used
	 * @param id The chatroom ID
	 */
	public void removeRoom(int id) {
		Statement stmt = null;
		Connection conn = ConnectionManager.makeConnection();
		
		try {
		    stmt = conn.createStatement();
		    //Execute query
		    stmt.executeUpdate("DELETE FROM chatroom WHERE room_id = " + id + ";");
		    stmt.executeUpdate("DELETE FROM messages WHERE room_id = " + id + ";");
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		  
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	/**
	 * Send a message to the messages table
	 * @param id- The chatroom ID
	 * @param message- The String message (To be replaced with an encryption algorithm later)
	 */
	public void sendMessage(int id, String message) {
		Statement stmt = null;
		Connection conn = ConnectionManager.makeConnection();
		try {
		    stmt = conn.createStatement();
		    //Execute query
		    stmt.executeUpdate("INSERT INTO messages VALUES("+ id +   ",\""  + message + "\");");
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		  
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	
	/**
	 * Make a connection to the database server
	 * @return A Connection object
	 */
	
	public void addMember(int roomId) {
		Statement stmt = null;
		Connection conn;
		ResultSet rs;
		conn = ConnectionManager.makeConnection();
		int currMembers = 0;
		try {
		    stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT curr_members FROM chatroom WHERE room_id = " + roomId + ";");
			currMembers = rs.getInt("curr_members");
			currMembers++;
			stmt.executeUpdate("UPDATE chatroom SET curr_members = " + currMembers + " WHERE room_id = " + roomId);
		    
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		
	}
}
