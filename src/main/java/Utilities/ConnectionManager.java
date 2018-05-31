package Utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public interface ConnectionManager {

	
	public static Connection makeConnection() {
		Connection conn = null;
		try {
			//Connect to database
			File configFile = new File("C:/VoidChat/config.txt");
			Scanner myScan = new Scanner(configFile);
			String hostName = null;
			String user = null;
			String pass = null;
			
			while (myScan.hasNextLine()) {
				user = myScan.nextLine();
				pass=myScan.nextLine();
				hostName = myScan.nextLine();
			}
			
			conn = DriverManager.getConnection(String.format("jdbc:mysql://%s/voidchat?" +
			        "user=%s&password=%s",hostName, user, pass));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
